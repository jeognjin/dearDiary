package board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardPage;

public interface BoardDao {
	
	//전체 게시글 갯수
	int BoardListTotalCount() throws SQLException;

	//현재 페이지에 해당하는 게시글 정보를 리스트에 저장
	List<Board> BoardCurrentPageList(int startRow, int size) throws SQLException;

	//글쓰기 db에 저장
	Map<String, Integer> insert(Board board) throws SQLException;

	//글번호로 게시글정보 가져오기
	Board selectContentOfBoardNo(int boardNo) throws SQLException;

	//글번호로 게시글 삭제하기
	int deleteBoardOfBoardNo(int boardNo) throws SQLException;

	//글정보 들고가서 글 수정하기
	int updateBoard(Board board) throws SQLException;



	

	


}
