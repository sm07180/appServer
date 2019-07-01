package com.hanul.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reply.ReplyServiceImpl;
import reply.ReplyVO;

@Controller
public class ReplyController {
	@Autowired private ReplyServiceImpl service;
	
	@RequestMapping("/replyDelete")
	public String replyInsert(int reply_id, Model model) {
		model.addAttribute("result", service.replyDelete(reply_id));
			System.out.println("댓글 삭제 하러 온두앗");
		return "reply/replyDelete";
	}
	
	@RequestMapping("/replyInsert")
	public String replyInsert(ReplyVO vo, Model model) {
		model.addAttribute("result", service.replyInsert(vo));
		
		return "reply/replyInsert";
	}
	
	@RequestMapping("/replyList")
	public String replyList(@RequestParam String board_id, Model model) {
		//reply_content, user_id, board_id 필요
		model.addAttribute("list", service.replyList(board_id));

		return "reply/replyList";
	}
}
