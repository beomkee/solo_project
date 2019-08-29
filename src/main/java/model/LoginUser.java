package model;

public class LoginUser {
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private String birth;
	private String manager_num;
	private String f_num;
	private String pl_num;
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getManager_num() {
		return manager_num;
	}

	public void setManager_num(String manager_num) {
		this.manager_num = manager_num;
	}

	public String getF_num() {
		return f_num;
	}

	public void setF_num(String f_num) {
		this.f_num = f_num;
	}

	public String getPl_num() {
		return pl_num;
	}

	public void setPl_num(String pl_num) {
		this.pl_num = pl_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"passwd\":\"" + passwd + "\", \"name\":\"" + name + "\", \"tel\":\"" + tel
				+ "\", \"birth\":\"" + birth + "\", \"manager_num\":\"" + manager_num + "\", \"f_num\":\"" + f_num
				+ "\", \"pl_num\":\"" + pl_num + "\", \"email\":\"" + email + "\"}";
	}

}
