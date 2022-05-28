package member.service;

import java.sql.SQLException;
import java.util.Map;

import member.dto.Member;

public interface MemberService {

	int regist(Map<String, String> memberInfo) throws SQLException;

	int changeInfo(Map<String, String> memberInfo) throws SQLException;

	int deleteMember(Map<String, String> deleteInfo) throws SQLException;

	Member selectOne(Map<String, String> deleteInfo) throws SQLException;

	

}
