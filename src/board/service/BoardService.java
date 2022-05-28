package board.service;

import java.sql.SQLException;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardPage;

public interface BoardService {

	//페이지 번호에 해당하는 게시글 정보 가져오기
	BoardPage getBoardPage(int pageNo) throws SQLException;

	//글쓰기 db에 저장
	Map<String, Integer> boardWrite(Board board) throws SQLException;

	//글번호로 게시글정보 가져오기
	Board viewBoard(int boardNo) throws SQLException;

	//글번호로 게시글 삭제하기
	int deleteBoard(int boardNo) throws SQLException;

	//글정보 들고가서 글 수정하기
	int modifyBoard(Board board) throws SQLException;




	
}
