package member;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sql;
	
	public void car_recorddelete(LoginMemberVO vo) {
		sql.delete("member-mapper.car_recorddelete", vo);
	}
	

	public void car_recordinsert(LoginMemberVO vo) {
		sql.insert("member-mapper.car_recordinsert", vo);
	}
	
	public String myinfo_update(LoginMemberVO vo) {
		if (sql.update("member-mapper.myinfo_update", vo) > 0) {
			sql.update("member-mapper.myinfo_update2", vo);
			return "success";
		} else {
			return "fail";
		}
	}

	public boolean insert(MemberVO vo) {
		return sql.insert("member-mapper.insert", vo) > 0 ? true : false;
	}

	public String id_check(String user_id) {
		return sql.selectOne("member-mapper.id_check", user_id) == null ? "success" : "fail";
	}

	public String login(HashMap<String, String> map) {
		return sql.selectOne("member-mapper.login", map) != null ? "success" : "fail";
	}

	public LoginMemberVO loginMember(String user_id) {
		return sql.selectOne("member-mapper.loginMemeber", user_id);
	}

	public List<MemberVO> list() {
		return sql.selectList("member-mapper.list");
	}

	public void authorityUpdate(MemberVO vo) {
		if (vo.getAdmin().contains("Y")) {
			sql.update("member-mapper.revokeAuthority", vo);
		} else {
			sql.update("member-mapper.grantAuthority", vo);
		}
	}

	public void memberDelete(String user_id) {
		sql.delete("member-mapper.memberDelete", user_id);
	}
}
