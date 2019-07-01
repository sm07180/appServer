package repair;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepairDAO {
	@Autowired private SqlSession sql;
	public String pair_mileage(String user_id) {
		return sql.selectOne("repair-mapper.pair_mileage",user_id);
				
	}
	
	
	public List<RepairVO> repair_list() {
		return sql.selectList("repair-mapper.repair_list");
	}
}
