package company;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDAO {
	@Autowired private SqlSession sql;
	
	public List<String> comp_id() {
		return sql.selectList("comp-mapper.comp_id");
	}
	
	public List<String> car_name(String comp_id) {
		return sql.selectList("comp-mapper.car_name",comp_id);
	}
}
