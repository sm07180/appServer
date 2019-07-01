package car_info;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import car_record.Car_recordVO;
import member.LoginMemberVO;

@Repository
public class Car_infoDAO {
   @Autowired private SqlSession sql;
   
   
	public String info_insert(LoginMemberVO vo) {
		return sql.insert("car_info-mapper.info_insert", vo) > 0 ? "success" : "fail";
	}
   
   
   
   public Car_recordVO myCarEff(String user_id) {
      Car_recordVO vo = sql.selectOne("car_info-mapper.myCarEff", user_id);
      if(vo.getOil() != 0) {
         vo.setAvg_mileage(Integer.parseInt(vo.getCar_mileage()) / vo.getOil());
      }else {
         vo.setAvg_mileage(0);
      }
      
      return vo;
   }
   
   public String efficiency(String car_name) {
      return sql.selectOne("car_info-mapper.efficiency", car_name);
   }
   
   public String rank(HashMap<String, String> map) {
		return sql.selectOne("car_info-mapper.rank", map);
	}
   
   public void first_record(LoginMemberVO vo) {
		sql.insert("car_info-mapper.first_record",vo);
	}
   
	public void mycar_star(Car_infoVO vo) {
		sql.update("car_info-mapper.mycar_star", vo);
	}
}