package auth.dao;

import java.sql.SQLException;
import java.util.Map;

import auth.dto.Auth;

public interface AuthDao {

	Auth selectLogin(Map<String, String> loginInfo) throws SQLException;



}
