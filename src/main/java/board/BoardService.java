package board;

import java.util.List;

public interface BoardService {
   List<BoardVO> list(BoardVO vo);
   String insert(BoardVO vo);
   String delete(String board_id);
   void boandcnt(BoardVO vo);
   String update(BoardVO vo);
   String update_fileNO(BoardVO vo);
   String sympathy(BoardVO vo);
}