package car_info;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import car_record.Car_recordVO;
import member.LoginMemberVO;


@Service
public class Car_infoServiceImpl implements Car_infoService{
	@Autowired private Car_infoDAO dao;

	@Override
	public Car_recordVO myCarEff(String user_id) {
		return dao.myCarEff(user_id);
	}
	
	@Override
	public String efficiency(String car_name) {
		return dao.efficiency(car_name);
	}
	
	@Override
	public String rank(HashMap<String, String> map) {
		return dao.rank(map);
	}

	@Override
	public String info_insert(LoginMemberVO vo) {
		return dao.info_insert(vo);
	}

	
	@Override
	public void first_record(LoginMemberVO vo) {
		dao.first_record(vo);
	}

	@Override
	public void mycar_star(Car_infoVO vo) {
		dao.mycar_star(vo);
	}
}
