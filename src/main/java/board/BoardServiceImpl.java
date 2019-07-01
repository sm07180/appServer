package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	// 게시판 목록 불러오기
	@Override
	public List<BoardVO> list(BoardVO vo) {
		return dao.list(vo);
	}

	// 게시판 글 등록하기
	@Override
	public String insert(BoardVO vo) {

		return dao.insert(vo);
	}
	
	//게시글 수정하기
	@Override
	public String update(BoardVO vo) {
		return dao.update(vo);
	}

	
	
	@Override
	public String update_fileNO(BoardVO vo) {
		return dao.update_fileNO(vo);
	}

	
	// 게시글 삭제
	@Override
	public String delete(String board_id) {

		return dao.delete(board_id);
	}

	//게시판 조회수
	@Override
	public void boandcnt(BoardVO vo) {
			dao.boandcnt(vo);
	}

	
	//공감 cnt
	@Override
	public String sympathy(BoardVO vo) {
		return dao.sympathy(vo);
	}
}