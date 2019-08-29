package model;

public class Manufactures {
	private String mf_num;
	private String f_num;
	private String pl_num;
	private String e_id;
	private String p_num;
	private String mf_count;
	private String mf_date;
	
	public String getMf_num() {
		return mf_num;
	}
	public void setMf_num(String mf_num) {
		this.mf_num = mf_num;
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
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getMf_count() {
		return mf_count;
	}
	public void setMf_count(String mf_count) {
		this.mf_count = mf_count;
	}
	public String getMf_date() {
		return mf_date;
	}
	public void setMf_date(String mf_date) {
		this.mf_date = mf_date;
	}
	@Override
	public String toString() {
		return " {\"mf_num\":\"" + mf_num + "\", \"f_num\":\"" + f_num + "\", \"pl_num\":\"" + pl_num + "\", \"e_id\":\"" + e_id
				+ "\", \"p_num\":\"" + p_num + "\", \"mf_count\":\"" + mf_count + "\", \"mf_date\":\"" + mf_date + "\"}";
	}
	
	
}
