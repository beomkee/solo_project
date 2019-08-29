package model;

public class LeftMenu {
	
	private int num;
	private String text;
	private int depth;
	private String collapse;
	private int division;
	private String url;
	private String use_yn;
	private int parent_num;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getCollapse() {
		return collapse;
	}
	public void setCollapse(String collapse) {
		this.collapse = collapse;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public int getParent_num() {
		return parent_num;
	}
	public void setParent_num(int parent_num) {
		this.parent_num = parent_num;
	}
	@Override
	public String toString() {
		return "{\"num\":\"" + num + "\", \"text\":\"" + text + "\", \"depth\":\"" + depth + "\", \"collapse\":\"" + collapse
				+ "\", \"division\":\"" + division + "\", \"url\":\"" + url + "\", \"use_yn\":\"" + use_yn + "\", \"parent_num\":\"" + parent_num + "\"}";
	}
	
	
	
}
