package auth.dto;

public class Auth {

	String memberId;
	String nickName;
	
	public Auth(String memberId, String nickName) {
		this.memberId = memberId;
		this.nickName = nickName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	
	
	
}
