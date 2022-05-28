package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import member.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private static Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private static MemberDaoImpl instance;

	private MemberDaoImpl() {
	}

	// 싱글톤
	public static MemberDaoImpl getInstance() {
		if (instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}

	// 회원가입 : memberInfo에서 뺀 값을 sql문에 넣어 executeUpdate하고 업데이트 결과값 리턴
	@Override
	public int insert(Map<String, String> memberInfo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "insert into member(memberid,password,nickName,email) values(?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberInfo.get("memberId"));
		pstmt.setString(2, memberInfo.get("password"));
		pstmt.setString(3, memberInfo.get("nickName"));
		pstmt.setString(4, memberInfo.get("email"));
		return pstmt.executeUpdate();
	}

	// 회원정보변경 : memberInfo에서 뺀 값을 sql문에 넣어 executeUpdate하고 업데이트 결과값 리턴
	@Override
	public int update(Map<String, String> memberInfo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "update member set password = ?, email = ? where memberid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberInfo.get("password"));
		pstmt.setString(2, memberInfo.get("email"));
		pstmt.setString(3, memberInfo.get("memberId"));
		return pstmt.executeUpdate();
	}

	//회원탈퇴 : deleteInfo에서 뺀 값을 sql문에 넣어 executeUpdate하고 업데이트 결과값 리턴
	@Override
	public int delete(Map<String, String> deleteInfo) throws SQLException {
		String sql = "delete from member where memberid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, deleteInfo.get("memberId"));
		System.out.println("MemberDaoImpl>>>memberInfo.get(\"deleteInfo\")>>>"+deleteInfo.get("memberId"));
		return pstmt.executeUpdate();
	}

	//회원정보 가져오기 : memberInfo에서 sql문에 넣어 가져온 정보를 멤버 객체로 반환
	@Override
	public Member selectOne(Map<String, String> memberInfo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "select * from member where memberid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberInfo.get("memberId"));
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String memberId = rs.getString("memberid");
			String password = rs.getString("password");
			String nickName = rs.getString("nickname");
			String email = rs.getString("email");
			return new Member(memberId, password, nickName, email);
		}
		return null;
	}
	

}
