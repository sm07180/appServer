package reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired private ReplyDAO dao;

	@Override
	public List<ReplyVO> replyList(String board_id) {
		return dao.replyList(board_id);
	}

	@Override
	public String replyInsert(ReplyVO vo) {
		return dao.replyInsert(vo);
	}

	@Override
	public String replyDelete(int reply_id) {
		return dao.replyDelete(reply_id);
	}
}
