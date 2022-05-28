package auth.service;

import java.sql.SQLException;
import java.util.Map;

import auth.dao.AuthDao;
import auth.dao.AuthDaoImpl;
import auth.dto.Auth;

public class AuthServiceImpl implements AuthService{
	
	private AuthDao authDao = AuthDaoImpl.getInstance();

	private static AuthServiceImpl instance;

	private AuthServiceImpl() {
	}

	//싱글톤
	public static AuthServiceImpl getInstance() {
		if (instance == null) {
			instance = new AuthServiceImpl();
		}
		return instance;
	}

	//로그인
	@Override
	public Auth login(Map<String, String> loginInfo) throws SQLException {

		return authDao.selectLogin(loginInfo);
	}
}
