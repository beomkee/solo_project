package model;

public class Sales {

	private String s_num;
	private String mf_num;
	private String e_id;
	private String c_id;
	private String p_num;
	private String s_obtain_date;
	private String s_contract_sum;
	private String s_complete_date;
	
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	public String getMf_num() {
		return mf_num;
	}
	public void setMf_num(String mf_num) {
		this.mf_num = mf_num;
	}
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getS_obtain_date() {
		return s_obtain_date;
	}
	public void setS_obtain_date(String s_obtain_date) {
		this.s_obtain_date = s_obtain_date;
	}
	public String getS_contract_sum() {
		return s_contract_sum;
	}
	public void setS_contract_sum(String s_contract_sum) {
		this.s_contract_sum = s_contract_sum;
	}
	public String getS_complete_date() {
		return s_complete_date;
	}
	public void setS_complete_date(String s_complete_date) {
		this.s_complete_date = s_complete_date;
	}
	@Override
	public String toString() {
		return "{s_num:\"" + s_num + "\", mf_num:\"" + mf_num + "\", e_id:\"" + e_id + "\", c_id:\"" + c_id + "\", p_num:\""
				+ p_num + "\", s_obtain_date:\"" + s_obtain_date + "\", s_contract_sum:\"" + s_contract_sum
				+ "\", s_complete_date:\"" + s_complete_date + "\"}";
	}
	
	
}
