package member;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
	boolean insert(MemberVO vo);
	List<MemberVO> list();
	String login(HashMap<String, String> map);
	LoginMemberVO loginMember(String user_id);
	String id_check(String user_id);
	String myinfo_update(LoginMemberVO vo);
	void authorityUpdate(MemberVO vo);
	void memberDelete(String user_id);
	void car_recordinsert(LoginMemberVO vo);
	void car_recorddelete(LoginMemberVO vo);
	/*boolean update(MemberVO vo);*/
	/*LoginMemberVO loginMember(String user_id);*/
}
