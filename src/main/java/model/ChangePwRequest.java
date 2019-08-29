package model;

public class ChangePwRequest {
	private String id;
	private String passwd;
	private String passwdCheck;
	private String tel;
	public boolean isPasswordEqualToConfirm() {
		return passwd != null && passwd.equals(passwdCheck);
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswdCheck() {
		return passwdCheck;
	}
	public void setPasswdCheck(String passwdCheck) {
		this.passwdCheck = passwdCheck;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
