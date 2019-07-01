package reply;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	@Autowired private SqlSession sql;
	
	public List<ReplyVO> replyList(String board_id) {
		return sql.selectList("reply-mapper.replyList", board_id);
	}
	
	public String replyInsert(ReplyVO vo) {
		return sql.insert("reply-mapper.replyInsert", vo) > 0 ? "success" : "fail";
	}
	
	public String replyDelete(int reply_id) {
		return sql.delete("reply-mapper.replyDelete", reply_id) > 0 ? "success" : "fail";
	}
}
