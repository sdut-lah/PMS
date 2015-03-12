package cn.sdut.entity;

public class GoodsOrderBean {
	
	int g_id;
	String gname;
	int gnumber;
	double gprice;
	String gsupplier;
	String operater;
	String gtime;
	int state;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getGtime() {
		return gtime;
	}
	public void setGtime(String gtime) {
		this.gtime = gtime;
	}
	
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGnumber() {
		return gnumber;
	}
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public String getGsupplier() {
		return gsupplier;
	}
	public void setGsupplier(String gsupplier) {
		this.gsupplier = gsupplier;
	}
	public String getOperater() {
		return operater;
	}
	public double getGprice() {
		return gprice;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}

}
