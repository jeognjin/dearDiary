package auth.service;

import java.sql.SQLException;
import java.util.Map;

import auth.dto.Auth;

public interface AuthService {

	Auth login(Map<String, String> loginInfo) throws SQLException;

}
