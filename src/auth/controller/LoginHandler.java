package auth.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.dto.Auth;
import auth.service.AuthService;
import auth.service.AuthServiceImpl;
import common.CommandHandler;

public class LoginHandler implements CommandHandler {

	AuthService authService = AuthServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// get방식으로 접근하면 다시 로그인폼으로 돌려보냄
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/view/member/loginForm.jsp"; // 로그인 페이지로 이동
			// post방식으로 접근하면 processSubmit실행
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/loginForm.jsp"; 
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		// id, pw를 맵에 저장
		Map<String, String> loginInfo = new HashMap<>();
		loginInfo.put("memberId", request.getParameter("memberId"));
		loginInfo.put("password", request.getParameter("password"));
//		System.out.println("LoginHandler>>>request.getParameter(\"memberId\")>>>"+request.getParameter("memberId"));

		// error 결과를 담을 errors 맵 생성
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		// 유효성 검사 (null값이 있으면 다시 폼으로 돌려보냄)
		for (String val : loginInfo.keySet()) {
			if (loginInfo.get(val) == null || loginInfo.get(val).isEmpty()) {
				errors.put(val, true); // errors에 내용입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/loginForm.jsp";
			}
		}

		try {
			// 입력된 정보로 db에서 동일한 아이디를 조회하여 auth객체에 저장.
			Auth auth = authService.login(loginInfo);
			// auth에 정보가 있으면 로그인 정보 일치함
			if (auth == null) {
				// id나 비밀번호가 일치하지 않으면 error내용 입력 후 로그인폼으로 보냄
				errors.put("idOrPwNotMatch", true);
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/loginForm.jsp";
			}

			// 사용자 정보를 세션에 붙인 후 메인화면으로 보내기
			request.getSession().setAttribute("authUser", auth);
			return "/WEB-INF/view/member/main.jsp";

		} catch (SQLException e) {
			//익셉션 발생시 시스템창에 메시지 찍기
			System.out.println("LoginHandler>>> SQLException>>>>"+e.getMessage());
		} 

		return "/WEB-INF/view/member/loginForm.jsp";

	}

}
