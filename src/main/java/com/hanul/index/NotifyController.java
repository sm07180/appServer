package com.hanul.index;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import notify.NotifyServiceImpl;
import notify.NotifyVO;

@Controller
public class NotifyController {
	@Autowired private NotifyServiceImpl service;
	
	//공지사항
	@RequestMapping("/notify")
	public String popboard(Model model) {
		List<NotifyVO> vo = new ArrayList<NotifyVO>();  
		vo = service.notify_list();
		
		model.addAttribute("list", vo);
		System.out.println();
		System.out.println("공지사항 들어온다");
		return "board/list";
	}
	
}
