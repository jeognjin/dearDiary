package board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import common.CommandHandler;

public class ReadboardHandler implements CommandHandler {

	private BoardService boardService = BoardServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String boardNo = request.getParameter("boardNo");

		// 글번호로 db에서 글정보 조회 후 board객체에 저장해서 리퀘스트에 붙여서 read.jsp 페이지로 이동시킴
		Board board;
		try {
			board = boardService.viewBoard(Integer.parseInt(boardNo));
//			System.out.println("ReadboardHandler>>>Integer.parseInt(boardNo)>>>"+Integer.parseInt(boardNo));
			request.setAttribute("board", board);
//			System.out.println("ReadboardHandler>>>>board>>>"+board);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/view/board/read.jsp";
	}
}
