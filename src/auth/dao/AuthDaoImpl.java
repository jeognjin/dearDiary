package auth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import auth.dto.Auth;

public class AuthDaoImpl implements AuthDao {

	private static Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private static AuthDaoImpl instance;

	private AuthDaoImpl() {
	}

	// 싱글톤
	public static AuthDaoImpl getInstance() {
		if (instance == null) {
			instance = new AuthDaoImpl();
		}
		return instance;
	}

	// 로그인
	@Override
	public Auth selectLogin(Map<String, String> loginInfo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		//입력받은 정보에서 id가 동일한 select
		String sql = "select * from member where memberid = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginInfo.get("memberId"));
		rs = pstmt.executeQuery();
		if(rs.next()) {
			//입력된 pw와 db에 있는 pw 동일한지 체크
			if(loginInfo.get("password").equals(rs.getString("password"))) {
				//select한 값에서 id와 닉네임을 auth 객체로 만들어서 반환
				String memberId = rs.getString("memberid");
				String nickName = rs.getString("nickname");
				return new Auth(memberId, nickName);
			}else {
				return null; //비밀번호 다르면 null리턴
			}
			
			}		
			return null;
	}

}
