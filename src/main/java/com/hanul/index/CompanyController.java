package com.hanul.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import company.CompanyServiceImpl;

@Controller
public class CompanyController {
	@Autowired private CompanyServiceImpl service;
	
	
	@RequestMapping("/comp_id")
	public String comp_id(Model model) {
		
		model.addAttribute("list",service.comp_id());
		
		return "company/List";
	}
	@RequestMapping("/car_name")
	public String car_name(Model model,@RequestParam String comp_id) {
		comp_id = comp_id.trim();
		model.addAttribute("list",service.car_name(comp_id));
		return "company/name_List";
	}
	
	
}
