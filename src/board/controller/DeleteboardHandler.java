package board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import common.CommandHandler;

public class DeleteboardHandler implements CommandHandler {

	private BoardService boardService = BoardServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String boardNo = request.getParameter("boardNo");

		try {
			int result = boardService.deleteBoard(Integer.parseInt(boardNo));
			if(result > 0 ) { //result가 0이상이면 삭제 성공
				return "/WEB-INF/view/board/deleteBoardSuccess.jsp";
			}else { 
				return "/WEB-INF/view/board/read.do?boardNo="+boardNo; //실패 시 read.do로 돌려보내기
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "/WEB-INF/view/board/read.do?boardNo="+boardNo;
	}

}
