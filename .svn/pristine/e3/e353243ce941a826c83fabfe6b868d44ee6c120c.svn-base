package com.hanul.index;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import board.BoardServiceImpl;
import board.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardServiceImpl service;

	//공감 cnt 만들기
	@RequestMapping("/sympathy")
	public String sympathy(BoardVO vo,Model model) {
		System.out.println("공감 들어온다");
		model.addAttribute("result",service.sympathy(vo));
	
		
		return "board/sympathy";
	}
	
	
	
	//게시글 업데이트
	@RequestMapping(value = "/board_update", method = { RequestMethod.GET, RequestMethod.POST })
	public String board_update(Model model, HttpServletRequest req) {
		BoardVO vo = new BoardVO();
		String realImgPath = "";
		String filename = "";
		System.out.println("게시글 업데이트");
		
	

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String board_title = (String) req.getParameter("board_title");
		String board_content = (String) req.getParameter("board_content");
		String board_id = (String)req.getParameter("board_id");
		int boar_id = Integer.parseInt(board_id);
		
		
		// 업로드 타입 확인
		String uploadType = (String) req.getParameter("uploadType");
	
		
		//파일 경로 실제 저장경로
		String filepath = (String) req.getParameter("filepath");
		
		
		
		//이미지 파일 저장하기
		MultipartRequest multi = (MultipartRequest) req;
		MultipartFile file = multi.getFile("image");


			if (uploadType != null) {
				filename = file.getOriginalFilename();
				vo.setFilename(filename);
				System.out.println(filename);

				// 디렉토리 존재하지 않으면 생성
				makeDir(req);

				if (file.getSize() > 0) {
					realImgPath = req.getSession().getServletContext().getRealPath("/resources/images/upload/");

					System.out.println(filename + " : " + realImgPath);
					System.out.println("fileSize : " + file.getSize());

					// 이미지파일 저장
					try {
						file.transferTo(new File(realImgPath, filename));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					filename = "FileFail.jpg";
					realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/upload/" + filename);
					System.out.println(filename + " : " + realImgPath);

				}
			}else {
				
				vo.setFilepath("");
				vo.setMfilepath("");	
				
			}


		// DB에 가서 등록 처리하기

		vo.setBoard_title(board_title);
		vo.setBoard_content(board_content);
		vo.setBoard_id(boar_id);
		
		
		
		vo.setFilepath(filepath);
		vo.setMfilepath(realImgPath+filename);

		if(uploadType==null) {
		model.addAttribute("update", service.update_fileNO(vo));
		System.out.println("여기로 가냐");
		
		return "board/update";
			
		}
		

		// System.out.println(service.insert(vo));
		

		
		model.addAttribute("update", service.update(vo));
		
		return "board/update";
	}
	
	
	

	
	//게시판 조회수
	@RequestMapping("/boandcnt")
	public void boandcnt(BoardVO vo) {
		//System.out.println(vo.getBoard_id());
		//System.out.println(vo.getReadcnt());
		service.boandcnt(vo);
	}
	
	
	//게시판 조회 한다
	@RequestMapping("/board")
	public String popboard(Model model, String kind,String user_id) {
		BoardVO vo = new BoardVO();
		vo.setKind(kind);
		vo.setUser_id(user_id);
		//System.out.println(kind);
		
		model.addAttribute("list", service.list(vo));
		// System.out.println("게시판 들어온다");
		return "notify/notify_list";
	}

	
	
	
	
	//게시판 글삽인한다
	@RequestMapping(value = "/boardInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String board_insert(Model model, HttpServletRequest req) {
		BoardVO vo = new BoardVO();
		String realImgPath = "";
		String filename = "";
		System.out.println("anInsertMulti()");
		
	

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String board_title = (String) req.getParameter("board_title");
		String board_content = (String) req.getParameter("board_content");
		String user_id = (String) req.getParameter("user_id");
		String car_name = (String) req.getParameter("car_name");
		
		
		// 업로드 타입 확인
		String uploadType = (String) req.getParameter("uploadType");

		
		
		//파일 경로 실제 저장경로
		String filepath = (String) req.getParameter("filepath");
		
		
		
		//이미지 파일 저장하기
		MultipartRequest multi = (MultipartRequest) req;
		MultipartFile file = multi.getFile("image");


			if (uploadType != null) {
				filename = file.getOriginalFilename();
				vo.setFilename(filename);
				System.out.println(filename);

				// 디렉토리 존재하지 않으면 생성
				makeDir(req);

				if (file.getSize() > 0) {
					realImgPath = req.getSession().getServletContext().getRealPath("/resources/images/upload/");

					System.out.println(filename + " : " + realImgPath);
					System.out.println("fileSize : " + file.getSize());

					// 이미지파일 저장
					try {
						file.transferTo(new File(realImgPath, filename));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					filename = "FileFail.jpg";
					realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/upload/" + filename);
					System.out.println(filename + " : " + realImgPath);

				}
			}else {
				
				vo.setFilepath("");
				vo.setMfilepath("");	
				
			}


		// DB에 가서 등록 처리하기

		vo.setBoard_title(board_title);
		vo.setBoard_content(board_content);
		vo.setUser_id(user_id);
		
		
		vo.setCar_name(car_name);
		vo.setFilepath(filepath);
		vo.setMfilepath(realImgPath+filename);

		
		

		// System.out.println(service.insert(vo));
		

		
		model.addAttribute("insert", service.insert(vo));
		
		return "board/insert";
	}

	
	
	// 파일경로 잡아주기
	public void makeDir(HttpServletRequest req) {
		File f = new File(req.getSession().getServletContext().getRealPath("/resources"));
		if (!f.isDirectory()) {
			f.mkdir();
		}

		File f1 = new File(req.getSession().getServletContext().getRealPath("/resources/images"));
		if (!f1.isDirectory()) {
			f1.mkdir();
		}

		File f2 = new File(req.getSession().getServletContext().getRealPath("/resources/images/upload/"));
		if (!f2.isDirectory()) {
			f2.mkdir();
		}
	}

	
	// 이미지 사이즈 조정해주기
	private BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}


	
	
	
	@RequestMapping("/boardDelete")
	public String board_delete(String board_id, Model model) {
		System.out.println(board_id);
		model.addAttribute("list", service.delete(board_id));
		return "board/delete";
	}

}