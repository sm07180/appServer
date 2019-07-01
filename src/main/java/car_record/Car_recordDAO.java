package car_record;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Car_recordDAO {
	@Autowired private SqlSession sql;
	
	public List<Car_recordVO> chartData(String user_id) {
		return sql.selectList("car_record-mapper.chartData", user_id);
	}
	
	public String inputInsert(Car_recordVO vo) {
		int repair_id = sql.selectOne("car_record-mapper.getId", vo);
		vo.setRepair_id(repair_id);
		
		return sql.insert("car_record-mapper.inputInsert", vo) > 0 ? "success" : "fail";
	}
	
	public String oil_insert(Car_recordVO vo) {
		return sql.insert("car_record-mapper.oil_insert",vo) > 0 ? "success" : "fail";
	}
	
	public List<Car_recordVO> myRepairList(Car_recordVO vo) {
		return sql.selectList("car_record-mapper.myRepairList", vo);
	}
	
	public List<Car_recordVO> myOilList(Car_recordVO vo) {
		return sql.selectList("car_record-mapper.myOilList", vo);
	}
	
	public void record_delete(Car_recordVO vo) {
		sql.delete("car_record-mapper.record_delete", vo);
	}
	
}
