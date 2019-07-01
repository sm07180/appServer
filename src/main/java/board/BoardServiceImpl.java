package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	// �Խ��� ��� �ҷ�����
	@Override
	public List<BoardVO> list(BoardVO vo) {
		return dao.list(vo);
	}

	// �Խ��� �� ����ϱ�
	@Override
	public String insert(BoardVO vo) {

		return dao.insert(vo);
	}
	
	//�Խñ� �����ϱ�
	@Override
	public String update(BoardVO vo) {
		return dao.update(vo);
	}

	
	
	@Override
	public String update_fileNO(BoardVO vo) {
		return dao.update_fileNO(vo);
	}

	
	// �Խñ� ����
	@Override
	public String delete(String board_id) {

		return dao.delete(board_id);
	}

	//�Խ��� ��ȸ��
	@Override
	public void boandcnt(BoardVO vo) {
			dao.boandcnt(vo);
	}

	
	//���� cnt
	@Override
	public String sympathy(BoardVO vo) {
		return dao.sympathy(vo);
	}
}