package board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.dto.Board;
import board.dto.BoardPage;

public class BoardServiceImpl implements BoardService{

	private final int size = 10;
	
	private BoardDao boardDao = BoardDaoImpl.getInstance();
	
	private static BoardServiceImpl instance;

	private BoardServiceImpl() {
	}

	//싱글톤
	public static BoardServiceImpl getInstance() {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	//페이지번호에 해당하는 게시글정보 가져오기
	@Override
	public BoardPage getBoardPage(int pageNo) throws SQLException {
		//전체 게시글 갯수 조회 후 total에 값 저장 
		int total = boardDao.BoardListTotalCount();
		//현재 페이지에 해당하는 게시글 정보를 리스트에 저장
		List<Board> content = boardDao.BoardCurrentPageList((pageNo - 1) * size, size);
		//가져온 정보들을 BoardPage객체로 반환
		return new BoardPage(total, pageNo, size, content);
		
		
	}

	//글쓰기 db에 저장
	@Override
	public Map<String, Integer> boardWrite(Board board) throws SQLException {
		return boardDao.insert(board);
	}

	//글번호로 게시글정보 가져오기
	@Override
	public Board viewBoard(int boardNo) throws SQLException {
		return boardDao.selectContentOfBoardNo(boardNo);
	}

	//글번호로 게시글 삭제하기
	@Override
	public int deleteBoard(int boardNo) throws SQLException {
		return boardDao.deleteBoardOfBoardNo(boardNo);
	}

	//글정보 들고가서 글 수정하기
	@Override
	public int modifyBoard(Board board) throws SQLException {
		return boardDao.updateBoard(board);
	}

}
