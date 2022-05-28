package member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommandHandler;
import member.dto.Member;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class DeleteMemberHandler implements CommandHandler {

	private MemberService memberService = MemberServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// get방식으로 접근하면 deleteForm 페이지로 보냄
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/view/member/deleteForm.jsp"; //
			// post방식으로 접근하면 processSubmit실행
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/deleteForm.jsp";
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		// id, pw를 맵에 저장
		Map<String, String> deleteInfo = new HashMap<>();
		deleteInfo.put("memberId", request.getParameter("memberId"));
		deleteInfo.put("password", request.getParameter("password"));
//		System.out.println("DeleteMemberHandler>>request.getParameter(\"memberId\")>>"+request.getParameter("memberId"));

		// error 결과를 담을 errors 맵 생성
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		// 유효성 검사 (null값이 있으면 다시 폼으로 돌려보냄)
		for (String val : deleteInfo.keySet()) {
			if (deleteInfo.get(val) == null || deleteInfo.get(val).isEmpty()) {
				errors.put(val, true); // errors에 내용입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/deleteForm.jsp";
			}
		}

		
		
		try {
			Member member = memberService.selectOne(deleteInfo);
			if(member == null) {
				errors.put("idOrPwNotMatch", true); // errors에 내용입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/deleteForm.jsp";
			}else if(!member.getPassword().equals(request.getParameter("memberId"))) {
				errors.put("idOrPwNotMatch", true); // errors에 내용입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/deleteForm.jsp";
			}
			int result = memberService.deleteMember(deleteInfo);
			if(result > 0 ) { //result가 0이상이면 탈퇴 성공
				//세션삭제
				if (request.getSession() != null) {
					request.getSession().invalidate();
				}
				return "/WEB-INF/view/member/deleteMemberSuccess.jsp";
			}else { //탈퇴 실패
				errors.put("deleteMember", true); //변경실패 에러 입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/deleteForm.jsp"; //실패 시 다시 폼으로 넘어감
			}
		} catch (SQLException e) {
			//익셉션 발생시 시스템창에 메시지 찍기
			System.out.println("DeleteMemberHandler>>> SQLException>>>>"+e.getMessage());
		}
		
		return "/WEB-INF/view/member/deleteForm.jsp";
	}

}
