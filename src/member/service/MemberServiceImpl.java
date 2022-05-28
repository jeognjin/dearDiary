package member.service;

import java.sql.SQLException;
import java.util.Map;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.dto.Member;

public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao = MemberDaoImpl.getInstance();
	private static MemberServiceImpl instance;

	private MemberServiceImpl() {
	}
	//싱글톤
	public static MemberServiceImpl getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}
	
	//회원가입
	@Override
	public int regist(Map<String, String> memberInfo) throws SQLException {
		return memberDao.insert(memberInfo);
	}
	
	//회원 정보 변경
	@Override
	public int changeInfo(Map<String, String> memberInfo) throws SQLException {
		return memberDao.update(memberInfo);
	}
	
	//회원 탈퇴
	@Override
	public int deleteMember(Map<String, String> deleteInfo) throws SQLException {
		return memberDao.delete(deleteInfo);
	}
	
	//회원 정보 가져오기(1명)
	@Override
	public Member selectOne(Map<String, String> memberInfo) throws SQLException {
		return memberDao.selectOne(memberInfo);
	}
	
	
	
	
	
}
