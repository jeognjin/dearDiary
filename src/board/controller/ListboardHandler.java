package board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.BoardPage;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import common.CommandHandler;

public class ListboardHandler implements CommandHandler {

	BoardService boardService = BoardServiceImpl.getInstance(); 
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		//request 페이지번호가 없으면 1로 저장하고 번호가 있으면 int로 타입변환
		String pageNoTemp = request.getParameter("pageNo");
		System.out.println("ListboardHandler>>request.getParameter(\"pageNo\")>>"+request.getParameter("pageNo"));
		int pageNo = 1;
		if (pageNoTemp != null) {
			pageNo = Integer.parseInt(pageNoTemp);
		}
		
		//페이지번호를 가지고 db에서 pageNo에 해당하는 게시글정보를 반환
		BoardPage boardPage;
		try {
			boardPage = boardService.getBoardPage(pageNo);
			//리퀘스트에 게시글정보를 붙여서 리스트페이지로 이동시킴
			request.setAttribute("boardPage", boardPage); 
			
			return "/WEB-INF/view/board/list.jsp";
		} catch (SQLException e) {
			//익셉션 발생시 시스템창에 메시지 찍기
			System.out.println("ListboardHandler>>> SQLException>>>>"+e.getMessage());
		}
		
		
		return "/WEB-INF/view/member/main.jsp"; //리스트 목록 가져오기 실패할 경우 메인으로 반환시킴
		
	}

}
