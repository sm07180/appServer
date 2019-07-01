package member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;

	@Override
	public boolean insert(MemberVO vo) {
		return dao.insert(vo);
	}


	@Override
	public String id_check(String user_id) {
		return dao.id_check(user_id);
	}


	@Override
	public String login(HashMap<String, String> map) {
		return dao.login(map);
	}


	@Override
	public LoginMemberVO loginMember(String user_id) {
		return dao.loginMember(user_id);
	}


	@Override
	public String myinfo_update(LoginMemberVO vo) {
		return dao.myinfo_update(vo);
	}


	@Override
	public List<MemberVO> list() {
		return dao.list();
	}


	@Override
	public void authorityUpdate(MemberVO vo) {
		dao.authorityUpdate(vo);
	}


	@Override
	public void memberDelete(String user_id) {
		dao.memberDelete(user_id);
	}


	@Override
	public void car_recordinsert(LoginMemberVO vo) {
		dao.car_recordinsert(vo);
	}


	@Override
	public void car_recorddelete(LoginMemberVO vo) {
		dao.car_recorddelete(vo);
		
	}
}
