package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.dto.Board;
import board.dto.BoardPage;
import member.dto.Member;

public class BoardDaoImpl implements BoardDao {

	private static Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private static BoardDaoImpl instance;

	private BoardDaoImpl() {
	}

	// 싱글톤
	public static BoardDaoImpl getInstance() {
		if (instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	
	//전체 게시글 갯수
	@Override
	public int BoardListTotalCount() throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "select count(*) from board";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			//첫번째 결과값을 int 타입으로 반환
			return rs.getInt(1);
		}
		//게시글이 없으면 0 반환
		return 0;
	}


	//현재 페이지에 해당하는 게시글 정보 가져오기
	@Override
	public List<Board> BoardCurrentPageList(int startRow, int size) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "select * from board order by board_no desc limit ?, ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, size);
		rs = pstmt.executeQuery();
		List<Board> currentPageList = new ArrayList<>();
		while (rs.next()) {
			int boardNo = rs.getInt("board_no");
			String nickName = rs.getString("nickname");
			String title = rs.getString("title");
			Date regDate = rs.getDate("regdate");
			int readCount = rs.getInt("read_count");
			//게시글 정보를 리스트에 저장
			currentPageList.add(new Board(boardNo, nickName, title, regDate, readCount));
		}
		return currentPageList;
	}

	//글쓰기 db에 저장
	@Override
	public Map<String, Integer> insert(Board board) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "insert into board(memberid, nickname, title, content, photo, read_count) values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getMemberId());
		pstmt.setString(2, board.getNickName());
		pstmt.setString(3, board.getTitle());
		pstmt.setString(4, board.getContent());
		pstmt.setString(5, board.getPhoto());
		pstmt.setInt(6, board.getReadCount());
		int result = pstmt.executeUpdate(); 
		int boardNo = selectLastBoardNo();
		Map<String, Integer> resultAndBoardNo = new HashMap<String, Integer>();
		resultAndBoardNo.put("result", result);
		resultAndBoardNo.put("boardNo", boardNo);
		return resultAndBoardNo;
	}

	//마지막 글번호 가져오기
	private int selectLastBoardNo() throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "select max(board_no) from board";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next())
			return (rs.getInt(1));
		
		return 0;
	}

	//글번호로 게시글정보 가져오기
	@Override
	public Board selectContentOfBoardNo(int boardNo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "select * from board where board_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			String memberId = rs.getString("memberid");
			String nickName = rs.getString("nickname");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String photo = rs.getString("photo");
			Date regDate = rs.getDate("regdate");
			int readCount = rs.getInt("read_count");
			return new Board(boardNo, memberId, nickName, title, content, photo, regDate, readCount);
		}
		return null;
	}

	//글번호로 게시글 삭제하기
	@Override
	public int deleteBoardOfBoardNo(int boardNo) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "delete from board where board_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		return pstmt.executeUpdate();
	}

	//글정보 들고가서 글 수정하기
	@Override
	public int updateBoard(Board board) throws SQLException {
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		String sql = "update board set title = ?, content = ?, photo = ? where board_no = ?";
		pstmt = conn.prepareStatement(sql);		
		pstmt.setString(1, board.getTitle());
		pstmt.setString(2, board.getContent());
		pstmt.setString(3, board.getPhoto());
		pstmt.setInt(4, board.getBoardNo());
		return pstmt.executeUpdate(); 
	}




	

}
