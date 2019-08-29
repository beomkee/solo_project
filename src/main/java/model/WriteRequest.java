package model;

public class WriteRequest {

	private String passwd1;
	private String passwd2;

	public boolean isPasswordEqualToConfirm() {
		return passwd1 != null && passwd2.equals(passwd1);
	}

	public String getPasswd1() {
		return passwd1;
	}

	public void setPasswd1(String passwd1) {
		this.passwd1 = passwd1;
	}

	public String getPasswd2() {
		return passwd2;
	}

	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}

}
