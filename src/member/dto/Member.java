package member.dto;

import java.util.Date;

public class Member {
	String memberId;
	String password;
	String nickName;
	String email;
	Date regdate;
	
	//db에서 회원정보 가져올 때 사용
	public Member(String memberId, String password, String nickName, String email, Date regdate) {
		this.memberId = memberId;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.regdate = regdate;
	}

	//입력받은 회원정보 db로 넘길 때 사용
	public Member(String memberId, String password, String nickName, String email) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
