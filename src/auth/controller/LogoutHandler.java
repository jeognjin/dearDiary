package auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommandHandler;

public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 세션을 삭제하여 로그인때 세션에 붙였던 사용자 정보도 함께 삭제하여 로그아웃함
		HttpSession session = request.getSession(false); // 세션이 없으면 만들지 않는다.
		if (session != null) {
			session.invalidate();
		}

		//로그아웃 후 메인으로 이동시킴
		return "/WEB-INF/view/member/main.jsp"; 
	}

}
