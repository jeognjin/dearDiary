package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	//정보변경, 글쓰기, 글수정, 게시글보기, 글삭제 시 로그인 여부 확인해서 비로그인 상태이면 로그인페이지로 이동시킴
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);
		//세션이 없거나, 세션에 authUser가 없으면 로그인 페이지로 리다이렉트
		if (session == null || session.getAttribute("authUser") == null) {
			HttpServletResponse response = (HttpServletResponse)res;
			req.getAttribute("LoginCheckFilter");
			response.sendRedirect(request.getContextPath() + "/member/login.do");
		} else {
			//로그인 한 상태(세션에 authUser가 있음)면 필터 통과
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
