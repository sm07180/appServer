package com.hanul.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import repair.RepairServiceImpl;

@Controller
public class RepairController {
	@Autowired private RepairServiceImpl service;
	
	@RequestMapping("/repairList")
	public String repairList(Model model) {
		model.addAttribute("list", service.repair_list());
		
		return "repair/repairList";
	}
	
	@RequestMapping("/repair")
	public String popboard(Model model,String user_id) {
		model.addAttribute("list", service.repair_list());
		
		return "repair/pair_mileage";
	}
}
