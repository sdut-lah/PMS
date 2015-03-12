package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cn.sdut.entity.OrderBean;
import cn.sdut.util.SqlConnection;

public class OrderDao {
	
	/**
	 * 确认订单并得到订单号
	 */
	Connection con = null;
	Statement sql = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<OrderBean> list = null;
	public int getOderId(OrderBean order){
		
		con = SqlConnection.getConnection();
		String str = "insert into Orderinfo(name,number,price,client,ordertime) values(?,?,?,?,?)";
		String str1 = "select max(OderId) from Order";
		try {
			ps = con.prepareStatement(str);
			ps.setString(1,order.getName());
			ps.setInt(2,order.getNumber());
			ps.setDouble(3, order.getPrice());
			ps.setString(4, order.getClient());
			ps.setString(5,order.getOrderTime());
			ps.executeUpdate();
			
			rs = ps.executeQuery(str1);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				ps.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 显示所有订单信息
	 * @return
	 */
	public List<OrderBean> getOrderInfo(){
		
		con = SqlConnection.getConnection();
		String str = "select * from Order";
		OrderBean order = null;
		try {
		    sql = con.createStatement();
			rs = ps.executeQuery(str);
			
			while(rs.next()){
				order = new OrderBean();
				order.setId(rs.getInt("id"));
				order.setName(rs.getString("name"));
				order.setNumber(rs.getInt("number"));
				order.setPrice(rs.getDouble("price"));
			    order.setClient(rs.getString("client"));
				order.setOrderTime(rs.getString("orderTime"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
			    ps.close();
				con.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 销售排行
	 * @return
	 */
	public List<OrderBean> sortSellProduct(){
		
		list = new ArrayList<OrderBean>();
		con = SqlConnection.getConnection();
		String sql = "select * from orderinfo order by number desc";
		OrderBean order = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				order = new OrderBean();
				order.setId(rs.getInt("id"));
				order.setName(rs.getString("name"));
				order.setNumber(rs.getInt("number"));
				order.setPrice(rs.getDouble("price"));
			    order.setClient(rs.getString("client"));
				order.setOrderTime(rs.getString("orderTime"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(list);
		return list;
	}

}
