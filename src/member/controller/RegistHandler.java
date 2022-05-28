package member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommandHandler;
import member.service.MemberService;
import member.service.MemberServiceImpl;

//member/regist.do
//회원가입
public class RegistHandler implements CommandHandler {

	private MemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 회원가입을 눌러서 regist.do로 넘어올 때는 get 방식
		if (request.getMethod().equalsIgnoreCase("GET")) {
//			System.out.println("RegistHandler>>>>>>method:get>>>>");
			return "/WEB-INF/view/member/registForm.jsp"; // 회원가입 페이지로 이동시킴
		} else if (request.getMethod().equalsIgnoreCase("POST")) { // 회원정보 입력 후 넘어올땐 post방식
			System.out.println("RegistHandler>>>>>>method:post>>>>");
			return processSubmit(request, response);
		} else {
			// GET, POST 외 다른방식으로 접근할때 응답코드 지정
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/WEB-INF/view/member/registForm.jsp";
		}
	}

	// 회원정보를 입력받아서 post방식으로 접근하면 폼체크 후에 가입완료
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//memberInfo 맵을 생성하여 입력받은 정보 저장
		Map<String, String> memberInfo = new HashMap<String, String>();
		memberInfo.put("memberId", request.getParameter("memberId"));
		memberInfo.put("password", request.getParameter("password"));
		memberInfo.put("confirmPassword", request.getParameter("confirmPassword"));
		memberInfo.put("nickName", request.getParameter("nickName"));
		memberInfo.put("email", request.getParameter("email"));

//		System.out.println("RegistHandler>>>>>>request.getParameter(\"nickName\")>>>>>"+request.getParameter("nickName"));
		
		//회원가입이 진행되지 않는 결과를 담을 errors 맵 생성
		Map<String, Boolean> errors = new HashMap<>();
		
		//유효성 검사 (null값이 있으면 다시 폼으로 돌려보냄)
		for(String val : memberInfo.keySet()) {
			if(memberInfo.get(val) == null || memberInfo.get(val).isEmpty()) {
				errors.put(val, true); //errors에 내용입력
				request.setAttribute("errors", errors);
//				System.out.println("RegistHandler>>>val>>>"+val);
				return "/WEB-INF/view/member/registForm.jsp";
			}
		}
		//비밀번호와 확인이 동일하지 않을경우
		if(!memberInfo.get("password").equals(memberInfo.get("confirmPassword"))){
			errors.put("notMatchPassword", true); //errors에 내용입력
			request.setAttribute("errors", errors);
			return "/WEB-INF/view/member/registForm.jsp";
		}
		
		try {
			//memberService로 회원가입정보를 넘겨 결과값을 받는다 
			int result = memberService.regist(memberInfo);
			if(result > 0 ) { //result가 0이상이면 회원가입성공 (result는 db에 입력된 row를 숫자로 반환한다)
				return "/WEB-INF/view/member/registSuccess.jsp";
			}else { //회원가입 실패
				errors.put("FailToRegister", true); //등록실패 에러 입력
				request.setAttribute("errors", errors);
				return "/WEB-INF/view/member/registForm.jsp"; //실패 시 다시 폼으로 넘어감
			}
		} catch (SQLException e) {
			//익셉션 발생시 시스템창에 메시지 찍기
			System.out.println("RegistHandler>>> SQLException>>>>"+e.getMessage());
		}
		
		return "/WEB-INF/view/member/registForm.jsp"; 

	}




}
