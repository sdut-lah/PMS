package cn.sdut.entity;

public class OrderBean {
	
	private int id;
	private String name;
	private int number;
	private double Price;
	private String client;
	private String orderTime;
	
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	

}
