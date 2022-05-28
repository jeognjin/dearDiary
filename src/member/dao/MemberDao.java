package member.dao;

import java.sql.SQLException;
import java.util.Map;

import member.dto.Member;

public interface MemberDao {

	//회원가입
	int insert(Map<String, String> memberInfo) throws SQLException;

	//회원 정보 변경
	int update(Map<String, String> memberInfo) throws SQLException;

	//회원 탈퇴
	int delete(Map<String, String> deleteInfo) throws SQLException;

	//회원정보 가져오기(1명)
	Member selectOne(Map<String, String> memberInfo) throws SQLException;

	

}
