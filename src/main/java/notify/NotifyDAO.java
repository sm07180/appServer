package notify;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotifyDAO {
	@Autowired private SqlSession sql;
	
	public List<NotifyVO> notify_list() {
		return sql.selectList("notify-mapper.notify_list");
	}
}
