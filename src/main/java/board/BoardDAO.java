package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sql;

	public List<BoardVO> list(BoardVO vo) {
		return sql.selectList("board-mapper.list", vo);
	}

	public String insert(BoardVO vo) {

		return sql.insert("board-mapper.insert", vo) > 0 ? "success" : "fail";
	}

	public String delete(String board_id) {

		return sql.delete("board-mapper.delete", board_id) > 0 ? "success" : "fail";
	}

	public void boandcnt(BoardVO vo) {
		sql.update("board-mapper.boardcnt", vo);
	}

	public String update(BoardVO vo) {
		return sql.update("board-mapper.update", vo) > 0 ? "success" : "fail";
	}

	public String update_fileNO(BoardVO vo) {
		return sql.update("board-mapper.update_fileNO", vo) > 0 ? "success" : "fail";
	}

	public String sympathy(BoardVO vo) {
		if(sql.selectOne("board-mapper.sympathy", vo) != null) {
			return sql.delete("board-mapper.sympathy_delete", vo) > 0 ? "delete" : "fail";
		}else {
			return sql.insert("board-mapper.sympathy_insert", vo) > 0 ? "insert" : "fail";
		}
	}
}