package com.hanul.index;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import car_record.Car_recordServiceImpl;
import car_record.Car_recordVO;

@Controller
public class Car_recordController {
	@Autowired private Car_recordServiceImpl service;
	
	
	@RequestMapping("/record_delete")
	public String record_delete(Car_recordVO vo, Model model) {
		service.record_delete(vo);
		
		return "car_record/record_delete";
	}
	
	
	@RequestMapping("/myOilList")
	public String myOilList(Car_recordVO vo, Model model) {
		model.addAttribute("list", service.myOilList(vo));
		
		return "car_record/myOilList";
	}
	
	
	
	@RequestMapping("/myRepairList")
	public String myRepairList(Car_recordVO vo, Model model) {
		model.addAttribute("list", service.myRepairList(vo));
		
		return "car_record/myRepairList";
	}
	
	//주유기록 입력하기
	@RequestMapping(value = "/oilinsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String oil_insert(HttpServletRequest req, Model model) {
		Car_recordVO vo = new Car_recordVO();
		

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String user_id = (String) req.getParameter("user_id");
		String month = (String) req.getParameter("month");
		String car_mileage = (String)req.getParameter("car_mileage");
		String st_oil_cost = (String)req.getParameter("oil_cost");
		int oil_cost = Integer.parseInt(st_oil_cost);
		String st_oil = (String)req.getParameter("oil");
		String oilcomp = (String)req.getParameter("com_nm");
		
		int oil = Integer.parseInt(st_oil);
		
		vo.setUser_id(user_id);
		vo.setMonth(month);
		vo.setCar_mileage(car_mileage);
		vo.setOil_cost(oil_cost);
		vo.setOil(oil);
		vo.setMemo(oilcomp);
		
		model.addAttribute("result", service.oil_insert(vo));
		
//		System.out.println(oilcomp);
//		System.out.println(user_id);
//		System.out.println(month);
//		System.out.println(car_mileage);
//		System.out.println(oil_cost);
//		System.out.println(oil);
		return "car_record/oilinsert";
	}
	
	//정기비록 입력하기
	@RequestMapping("/inputInsert")
	public String inputInsert(Car_recordVO vo, Model model) {
		model.addAttribute("result", service.inputInsert(vo));
		
		return "car_record/input";
	}
	
	
	//정비금액 주유금액 차트목록화
	@RequestMapping("/chartData")
	public String oilCost(@RequestParam String user_id, Model model) {
		List<Car_recordVO> list = service.chartData(user_id);
		
		String month_oil_cost = "";
		String month_repair_cost = "";
		String month_oil = "";
		String month_mileage = "";
		
		//월별 주유 금액
		String ck = "";
		int temp = 0;
		for(int i=0; i<list.size(); i++) {
			if(temp<9) {
				ck += "0" + (temp+1);
			}else {
				ck += (temp+1);
			}
			
			if(ck.contains(list.get(i).getMonth())) {
				month_oil_cost += list.get(i).getMonth_oil_cost() + ",";
				month_repair_cost += list.get(i).getMonth_repair_cost() + ",";
				month_oil += list.get(i).getMonth_oil() + ",";
				
				if(!list.get(i).getMax_mileage().trim().equals("0") && i != 0) {
					month_mileage += Integer.parseInt(list.get(i).getMax_mileage()) - Integer.parseInt(list.get(i-1).getMax_mileage()) + ",";
				}else {
					month_mileage += list.get(i).getMonth_mileage() + ",";
				}
			}else {
				month_oil_cost += "0,";
				month_repair_cost += "0,";
				month_oil += "0,";
				month_mileage += "0,";
			}
			
			temp++;
			ck = "";
			
			if(list.size() == temp ) {
				break;
			}
		}
		
		for(int i=temp; i<12; i++) {
			month_oil_cost += "0,";
			month_repair_cost += "0,";
			month_oil += "0,";
			month_mileage += "0,";
		}
		
//		System.out.println("oc : " + month_oil_cost);
//		System.out.println("or : " + month_repair_cost);
//		System.out.println("oo : " + month_oil);
//		System.out.println("mm : " + month_mileage);
		model.addAttribute("month_oil_cost", month_oil_cost);
		model.addAttribute("month_repair_cost", month_repair_cost);
		model.addAttribute("month_oil", month_oil);
		model.addAttribute("month_mileage", month_mileage);
		
		
		return "chart/chartData";
	}
}
