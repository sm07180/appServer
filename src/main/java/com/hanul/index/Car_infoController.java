package com.hanul.index;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import car_info.Car_infoServiceImpl;
import car_info.Car_infoVO;
import car_record.Car_recordVO;
import member.LoginMemberVO;

@Controller
public class Car_infoController {
	@Autowired
	private Car_infoServiceImpl service;
	
	
	//오늘 시간 만들기
	 private String time() {
	        SimpleDateFormat sdf2 = new SimpleDateFormat("kkmm");
	        Date now = new Date();
	        int val = Integer.parseInt(sdf2.format(now));
	        if (val >= 210 && val <= 510) {
	            return "0200";
	        } else if (val > 510 && val <= 810) {
	            return "0500";
	        } else if (val > 810 && val <= 1110) {
	            return "0800";
	        } else if (val > 1110 && val <= 1410) {
	            return "1100";
	        } else if (val > 1410 && val <= 2010) {
	            return "1400";
	        } else if (val > 2010 && val <= 2310) {
	            return "1700";
	        } else {
	            return "2300";
	        }
	    }
	 
	 
	 //주유소의 가격 가져오기
	 @RequestMapping("/oilcompy_code")
	 public String oilcompy_code(String compy_code,Model model) {
		System.out.println(compy_code);
		String oil_url = "http://www.opinet.co.kr/api/detailById.do";
			
		StringBuilder url = new StringBuilder(oil_url);
		url.append("?code=F519190417");
		url.append("&id="+compy_code);
		url.append("&out=json");
		

		String result = url.toString();

		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(result).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			result = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result = result.substring(result.indexOf("[")+2, result.indexOf("]") + 1).trim();
		result = result.substring(result.indexOf("["), result.indexOf("]") + 1).trim();
		String compy_oilprice ="";
		String PRICE = "";
	       JSONArray jsonArray = new JSONArray(result);
	       //System.out.println(jsonArray.length());
	       for (int i = 0; i < jsonArray.length(); i++) {
	    	   
	           JSONObject row = jsonArray.getJSONObject(i);
	           //System.out.println(row);
	           String PRODCD = (String) row.getString("PRODCD");
	          
	           compy_oilprice += PRODCD+",";
	           String pric = String.valueOf(row.getInt("PRICE"));
	           compy_oilprice += pric+"!!";
	           //System.out.println(pric);
	           
	       }
	
		
		
		model.addAttribute("result", compy_oilprice);
		
		 return "car_info/oilcompy_code";
	 }
	 
	 
	 
	// 오늘의 날씨를 알아보자
	@RequestMapping("/weather")
	public String weather(String weather_x,String weather_y,Model model) {
		//	System.out.println(weather_x);
		//	System.out.println(weather_y);
		int fcstValue;
 		String today_weather ="";
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		 //성민
		 String key = "Xy5yLMuI2f9%2Fz7KtvVjEKEOZraKkf0%2F10rrIHZI4yG5ZnLEi2l%2BLB1%2BInn1PRTGuLmzUtQonrLUfoj1BvlBpaA%3D%3D";
		 //처리
         String key2 = "7yVeHeB4RQMlqi6UOTxYhZ5Px5Est6psR%2F66FQBNsBaaUzE5nV5yU0lEVGqw1s2dQwuI0dIu3T4KMw0cswBaFw%3D%3D";
		 Date now = new Date();
         String urlstr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
         String ServiceKey = "?ServiceKey=" + key2;
         String date = "&base_date=" + sdf1.format(now);
         String time = "&base_time=" + time();
         String nx_ny = "&nx=" + weather_x + "&ny=" + weather_y;
         String type = "&_type=json";
         

         String total_url = urlstr + ServiceKey + date + time + nx_ny + type;
         String result = "";
         HttpURLConnection conn;
         try {
 			conn = (HttpURLConnection) new URL(total_url).openConnection();
 			conn.setRequestMethod("GET");
 			conn.setRequestProperty("Content-type", "application/json");
 			//System.out.println("Response code: " + conn.getResponseCode());
 			BufferedReader rd;
 			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
 			StringBuilder sb = new StringBuilder();
 			String line;
 			while ((line = rd.readLine()) != null) {
 				sb.append(line);
 			}
 			rd.close();
 			conn.disconnect();
 			result = sb.toString();

 		} catch (Exception e) {
 			e.printStackTrace();
 		}
   //     System.out.println(result.indexOf("["));
 	//	System.out.println(result.indexOf("]") + 1);
         if(result.contains("[]")) {
 		result = result.substring(result.indexOf("["), result.indexOf("]")+1).trim();
 		
       JSONArray jsonArray = new JSONArray(result);
       for (int i = 0; i < jsonArray.length(); i++) {
           JSONObject row = jsonArray.getJSONObject(i);
           String category = (String) row.getString("category");
           
           if (category.equals("SKY")) {
               fcstValue = row.getInt("fcstValue");
               today_weather += String.valueOf(fcstValue)+",";
           } else if (category.equals("PTY")) {
               fcstValue = row.getInt("fcstValue");
               today_weather += String.valueOf(fcstValue)+",";
           } else if (category.equals("T3H")) {
               fcstValue = row.getInt("fcstValue");
               today_weather+=String.valueOf(fcstValue);
           }
       }

         }else {
        	 today_weather = "0,1,27,"; 
         }
  
 		String test = "0,1,27,"; 
       //model.addAttribute("today_weather", result);
      model.addAttribute("result", today_weather);
 	//model.addAttribute("result", test);
        
        return "car_info/weather";
	}

	
	
	// 내차 평가하기
	@RequestMapping("/mycar_star")
	public void mycar_star(Car_infoVO vo) {
		System.out.println("별점등록하러 온다");
		System.out.println(vo.getMycar_star());
		System.out.println(vo.getUser_id());
		service.mycar_star(vo);
	}

	// 주유소
	@RequestMapping("/katec")
	public String katec(Model model, String x, String y, String prodcd) {
		 System.out.println(x);
		 System.out.println(y);

		String oil_url = "http://www.opinet.co.kr/api/aroundAll.do";
		
		StringBuilder url = new StringBuilder(oil_url);
		url.append("?code=F519190417");
		url.append("&x=" + x);
		url.append("&y=" + y);
		url.append("&radius=" + 1000);
		url.append("&sort=1");
		url.append("&prodcd=" + prodcd);
		url.append("&out=json");

		String result = url.toString();

		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(result).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			result = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result.indexOf("["));
		System.out.println(result.indexOf("]") + 1);
		result = result.substring(result.indexOf("["), result.indexOf("]") + 1).trim();
		
		 //String result = "[{\"UNI_ID\":\"A0020470\",\"POLL_DIV_CD\":\"SOL\",\"OS_NM\":\"상공주유소\",\"PRICE\":1495,\"DISTANCE\":237.1,\"GIS_X_COOR\":298690.53533,\"GIS_Y_COOR\":284267.68539}, {\"UNI_ID\":\"A0020326\",\"POLL_DIV_CD\":\"SKE\",\"OS_NM\":\"우리셀프주유소\",\"PRICE\":1495,\"DISTANCE\":455.6,\"GIS_X_COOR\":298478.00000,\"GIS_Y_COOR\":284636.00000}, {\"UNI_ID\":\"A0020481\",\"POLL_DIV_CD\":\"SOL\",\"OS_NM\":\"남선석유(주)구도일주유소 청기와\",\"PRICE\":1495,\"DISTANCE\":508.5,\"GIS_X_COOR\":299245.00000,\"GIS_Y_COOR\":284063.00000}, {\"UNI_ID\":\"A0019802\",\"POLL_DIV_CD\":\"SOL\",\"OS_NM\":\"가람주유소(S)\",\"PRICE\":1509,\"DISTANCE\":524.8,\"GIS_X_COOR\":299365.76714,\"GIS_Y_COOR\":284609.19692}, {\"UNI_ID\":\"A0019683\",\"POLL_DIV_CD\":\"SKE\",\"OS_NM\":\"마트몰(주)대남로주유소\",\"PRICE\":1516,\"DISTANCE\":798.8,\"GIS_X_COOR\":298996.00000,\"GIS_Y_COOR\":283625.00000}, {\"UNI_ID\":\"A0020216\",\"POLL_DIV_CD\":\"HDO\",\"OS_NM\":\"형제주유소\",\"PRICE\":1549,\"DISTANCE\":260.2,\"GIS_X_COOR\":299120.91417,\"GIS_Y_COOR\":284508.63234}]";
		 
		 
		 model.addAttribute("result", result);

		return "car_info/oil_top10";
	}

	// 만족도, 연비순위 조회
	@RequestMapping("/garageList")
	public String garageList(@RequestParam String user_id, @RequestParam String car_name, Model model) {
		// 만족도, 연비순위
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("car_name", car_name);

		// 오너연비
		Car_recordVO vo = new Car_recordVO();

		String efficiency = service.efficiency(car_name);

		model.addAttribute("efficiency", efficiency);

		vo = service.myCarEff(user_id);
		int oil_cost = vo.getOil_cost();

		if (vo.getAvg_mileage() != 0) {
			DecimalFormat df = new DecimalFormat("#.0");
			double result = (vo.getAvg_mileage() + Integer.parseInt(efficiency)) / 2;
			model.addAttribute("rank", service.rank(map));

			double my_money = oil_cost / result;
			model.addAttribute("result", df.format(result));
			model.addAttribute("my_money", df.format(my_money));
		} else {
			model.addAttribute("result", efficiency);
			model.addAttribute("rank", "-");
			model.addAttribute("my_money", "-");
		}

		return "garage/garageList";
	}

	// 차정보 등록
	@RequestMapping(value = "/info_insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String info_insert(Model model, HttpServletRequest req) {

		LoginMemberVO vo = new LoginMemberVO();
		String realImgPath = "";
		String filename = "";
		System.out.println("anInsertMulti()");

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String user_id = (String) req.getParameter("user_id");
		String comp_id = (String) req.getParameter("comp_id");
		String car_name = (String) req.getParameter("car_name");
		String car_nickname = (String) req.getParameter("car_nickname");
		String car_mileage = (String) req.getParameter("car_mileage");
		vo.setUser_id(user_id);
		vo.setComp_id(comp_id);
		vo.setCar_name(car_name);
		vo.setCar_nickname(car_nickname);
		vo.setCar_mileage(car_mileage);

		// 업로드 타입 확인
		String uploadType = (String) req.getParameter("uploadType");

		// 파일 경로 실제 저장경로
		String filepath = (String) req.getParameter("filepath");

		// 이미지 파일 저장하기
		MultipartRequest multi = (MultipartRequest) req;
		MultipartFile file = multi.getFile("image");

		if (uploadType != null) {
			filename = file.getOriginalFilename();

			System.out.println(filename);

			// 디렉토리 존재하지 않으면 생성
			makeDir(req);

			if (file.getSize() > 0) {
				realImgPath = req.getSession().getServletContext().getRealPath("/resources/images/mycar_info/");

				System.out.println(filename + " : " + realImgPath);
				System.out.println("fileSize : " + file.getSize());
				vo.setCar_image(realImgPath + filename);

				// 이미지파일 저장
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
		} else {

			vo.setCar_image("");
			vo.setCar_mimage("");

		}

		vo.setCar_mimage(filepath);

		System.out.println("차정보 등록하러 온다");
		System.out.println(vo.getUser_id());
		System.out.println(vo.getCar_name());
		System.out.println(vo.getCar_nickname());
		System.out.println(vo.getCar_mileage());
		System.out.println(vo.getCar_image());
		System.out.println(vo.getCar_mimage());

		service.first_record(vo);

		model.addAttribute("list", service.info_insert(vo));

		return "car_info/check";
	}

	// 메인화면 연비 보여주기
	@RequestMapping("/mainList")
	public String mainList(@RequestParam String user_id, @RequestParam String car_name, Model model) {
		String efficiency = service.efficiency(car_name);
		model.addAttribute("efficiency", efficiency);

		Car_recordVO vo = new Car_recordVO();
		vo = service.myCarEff(user_id);

		if (vo.getAvg_mileage() != 0) {
			DecimalFormat df = new DecimalFormat("#.0");
			String result = df.format((vo.getAvg_mileage() + Integer.parseInt(efficiency)) / 2);
			model.addAttribute("result", result);
		} else {
			model.addAttribute("result", efficiency);
		}

		/* System.out.println(result); */

		return "main/mainList";
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

		File f2 = new File(req.getSession().getServletContext().getRealPath("/resources/images/mycar_info/"));
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

}