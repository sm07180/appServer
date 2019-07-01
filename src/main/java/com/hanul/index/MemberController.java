package com.hanul.index;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import member.LoginMemberVO;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberServiceImpl service;

	// ȸ��Ż��
	@RequestMapping("/memberDelete")
	public void memberDelete(String user_id) {
		System.out.println(user_id);
		System.out.println("ȸ������ ���´�");
		service.memberDelete(user_id);
	}

	// ���� �ο�/����
	@RequestMapping("/authorityUpdate")
	public void authorityUpdate(MemberVO vo) {
		System.out.println(vo.getAdmin());
		System.out.println(vo.getUser_id());
		service.authorityUpdate(vo);
	}

	// ���Ѻο� ������
	@RequestMapping("/memberList")
	public String memberList(Model model) {
		model.addAttribute("list", service.list());

		return "member/memberList";
	}

	// ȸ������ ����
	@RequestMapping(value = "/myinfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String myinfo(Model model, HttpServletRequest req) {
		/* public String myinfo(LoginMemberVO vo,Model model) { */
		System.out.println("ȸ�� ������Ʈ");
		LoginMemberVO vo = new LoginMemberVO();
		String realImgPath = "";
		String filename = "";
	

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String user_id = (String) req.getParameter("user_id");
		String car_nickname = (String) req.getParameter("car_nickname");
		String car_name = (String) req.getParameter("car_name");
		String comp_id = (String) req.getParameter("comp_id");
		String phone = (String) req.getParameter("phone");
		String user_pw = (String) req.getParameter("user_pw");

		String car_mileage = (String) req.getParameter("car_mileage");

		vo.setUser_id(user_id);
		vo.setCar_nickname(car_nickname);
		vo.setCar_name(car_name);
		vo.setComp_id(comp_id);
		vo.setPhone(phone);
		vo.setUser_pw(user_pw);
		
		// ���ε� Ÿ�� Ȯ��
		String uploadType = (String) req.getParameter("uploadType");
		vo.setMycar_star(uploadType);
		
		
		
		
		
		
		vo.setCar_mileage(car_mileage);
		System.out.println(user_id);
		System.out.println(car_nickname);
		System.out.println(car_name);
		System.out.println(comp_id);
		System.out.println(phone);
		System.out.println(user_pw);
		System.out.println(car_mileage);

		System.out.println(uploadType);
		if (uploadType.contains("image")) {
			// ���� ��� ���� ������
			String filepath = (String) req.getParameter("filepath");
			vo.setCar_mimage(filepath);

			// �̹��� ���� �����ϱ�
			MultipartRequest multi = (MultipartRequest) req;
			MultipartFile file = multi.getFile("image");
			
			
			
			if (uploadType.equals("image")) {
				filename = file.getOriginalFilename();
				System.out.println(filename);
				// ���丮 �������� ������ ����
				makeDir(req);

				if (file.getSize() > 0) {
					realImgPath = req.getSession().getServletContext().getRealPath("/resources/images/mycar_info/");

					System.out.println(filename + " : " + realImgPath);
					System.out.println("fileSize : " + file.getSize());
					vo.setCar_image(realImgPath + filename);
					// vo.setCar_mileage();

					// �̹������� ����
					try {
						file.transferTo(new File(realImgPath, filename));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					filename = "FileFail.jpg";
					realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/mycar_info/" + filename);
					System.out.println(filename + " : " + realImgPath);

				}
			} else if(uploadType.equals("image_delete")) {
				vo.setCar_image("");
				vo.setCar_mimage("");
			}
		}

		
		if (car_mileage.contains("old_car")) {
			model.addAttribute("result", service.myinfo_update(vo));
		} else {
			service.car_recorddelete(vo);
			service.car_recordinsert(vo);
			model.addAttribute("result", service.myinfo_update(vo));
		}
		 return "member/myinfo_update";
	}

	
	
	
	
	
	
	
	
	
	
	
	// ȸ������ Static ó���κ�
	@RequestMapping("/loginMember")
	public String loginMember(String user_id, Model model) {
		model.addAttribute("result", service.loginMember(user_id));

		return "member/MemberInfo";
	}

	// �α��� ó��
	@RequestMapping("/signIn")
	public String signIn(Model model, LoginMemberVO vo) {
		HashMap<String, String> map = new HashMap<String, String>();

		// System.out.println(vo.getUser_id());
		// System.out.println(vo.getUser_pw());

		map.put("user_id", vo.getUser_id());
		map.put("user_pw", vo.getUser_pw());

		model.addAttribute("result", service.login(map));

		return "member/login";
	}

	// id �ߺ� üũ �κ�
	@RequestMapping("/idCheck")
	public String idCheck(@RequestParam String user_id, Model model) {
		System.out.println("idCheck : " + user_id);
		model.addAttribute("user_id", service.id_check(user_id));

		return "join/idCheck";
	}

	// ȸ������
	@RequestMapping("/signUp")
	public String join(MemberVO vo) {
		if (service.insert(vo))
			System.out.println("signUp success");
		else
			System.out.println("signUp fail");

		return "join/signUp";
	}

	/* ������� ���� ���� �Դϴ� */
	// ���ϰ�� ����ֱ�
	public void makeDir(HttpServletRequest req) {
		File f = new File(req.getSession().getServletContext().getRealPath("/resources"));
		if (!f.isDirectory()) {
			f.mkdir();
		}

		File f1 = new File(req.getSession().getServletContext().getRealPath("/resources/images"));
		if (!f1.isDirectory()) {
			f1.mkdir();
		}

		File f2 = new File(req.getSession().getServletContext().getRealPath("/resources/images/mycar_info/"));
		if (!f2.isDirectory()) {
			f2.mkdir();
		}
	}

	// �̹��� ������ �������ֱ�
	private BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

}
