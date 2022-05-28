package member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.dto.Auth;
import common.CommandHandler;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class ChangeInfoHandler implements CommandHandler {

	private MemberService memberService = MemberServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 세션에 있는 로그인정보를 뽑아서 리퀘스트에 붙임
		Auth auth = (Auth) request.getSession().getAttribute("authUser");
		request.setAttribute("changeInfoId", auth.getMemberId());
		request.setAttribute("changeInfoNickname", auth.getNickname());
		// get방식으로 접근하면 정보변경 페이지로 이동시킴
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/view/member/changeInfoForm.jsp";
		} else if (request.getMethod().equalsIgnoreCase("POST")) { // 회원정보 입력 후 넘어올땐 post방식
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/changeInfoForm.jsp";
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		// memberInfo 맵을 생성하여 입력받은 정보 저장
		Map<String, String> memberInfo = new HashMap<String, String>();
		memberInfo.put("memberId", request.getParameter("memberId"));
		memberInfo.put("password", request.getParameter("password"));
		memberInfo.put("confirmPassword", request.getParameter("confirmPassword"));
		memberInfo.put("nickName", request.getParameter("nickName"));
		memberInfo.put("email", request.getParameter("email"));
		
		System.out.println("ChangeInfoHandler>>>request.getParameter(\"memberId\")>>>"+request.getParameter("memberId"));

		// error 결과를 담을 errors 맵 생성
		Map<String, Boolean> errors = new HashMap<>();

		// 유효성 검사 (null값이 있으면 다시 폼으로 돌려보냄)
		for (String val : memberInfo.keySet()) {
			if (memberInfo.get(val) == null || memberInfo.get(val).isEmpty()) {
				errors.put(val, true); // errors에 내용입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/changeInfoForm.jsp";
			}
		}

		// 비밀번호와 확인이 동일하지 않을경우
		if (!memberInfo.get("password").equals(memberInfo.get("confirmPassword"))) {
			errors.put("notMatchPassword", true); // errors에 내용입력
			request.setAttribute("errors", errors);
			return "/WEB-INF/view/member/changeInfoForm.jsp";
		}
		
		//memberService로 입력정보를 넘기고 업데이트 결과값을 받는다 
		try {
			int result = memberService.changeInfo(memberInfo);
			if(result > 0 ) { //result가 0이상이면 정보변경 성공 
				return "/WEB-INF/view/member/changeInfoSuccess.jsp";
			}else { //회원가입 실패
				errors.put("FailToChangeInfo", true); //변경실패 에러 입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/registForm.jsp"; //실패 시 다시 폼으로 넘어감
			}
		} catch (SQLException e) {
			//익셉션 발생시 시스템창에 메시지 찍기
			System.out.println("ChangeInfoHandler>>> SQLException>>>>"+e.getMessage());
		}
		
		return "/WEB-INF/view/member/changeInfoForm.jsp";
	}

}
