package board.dto;

import java.util.Date;

public class Board {

	private int boardNo;
	private String memberId;
	private String nickName;
	private String title;
	private String content;
	private String photo;
	private Date regDate;
	private int readCount;
	
	
	public Board(int boardNo, String nickName, String title, Date regDate, int readCount) {
		this.boardNo = boardNo;
		this.nickName = nickName;
		this.title = title;
		this.regDate = regDate;
		this.readCount = readCount;
	}

	

	public Board(int boardNo, String memberId, String nickName, String title, String content, String photo,
			Date regDate, int readCount) {
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.regDate = regDate;
		this.readCount = readCount;
	}

	
	

	public Board(int boardNo, String memberId, String nickName, String title, String content, String photo, int readCount) {
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.readCount = readCount;
	}



	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public Date getRegDate() {
		return regDate;
	}



	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}



	public int getReadCount() {
		return readCount;
	}



	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}



	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", memberId=" + memberId + ", nickName=" + nickName + ", title=" + title
				+ ", content=" + content + ", photo=" + photo + ", regDate=" + regDate + ", readCount=" + readCount
				+ "]";
	}

	
}
