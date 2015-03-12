package cn.sdut.entity;

public class ProductBean {
	
	int p_id;
	String pname;
	int pnumber;
	double Price;
	String supplier;
	String stockInTime;
	
	
	public int getP_id() {
		return p_id;
	}


	public void setP_id(int p_id) {
		this.p_id = p_id;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public int getPnumber() {
		return pnumber;
	}


	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}


	public double getPrice() {
		return Price;
	}


	public void setPrice(double price) {
		Price = price;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getStockInTime() {
		return stockInTime;
	}


	public void setStockInTime(String stockInTime) {
		this.stockInTime = stockInTime;
	}


	@Override
	public String toString(){
		
		return "id="+getP_id()+"\nName="+getPname()+"\nPrice="+getPrice()+"\nNum="+getPnumber()+"\nsupplier="+getSupplier()+"\ndate="+getStockInTime();
		
	}

}
