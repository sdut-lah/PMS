package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sdut.entity.ClientBean;
import cn.sdut.entity.SupplierBean;
import cn.sdut.util.SqlConnection;

public class BaseInfoDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * 增加客户信息
	 * 
	 * @param client
	 * @return
	 */
	public boolean addClient(ClientBean client) {

		con = SqlConnection.getConnection();
		String sql = "insert into client(cname,cphone,caddress,cbankAccount) values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getPhoneNumber());
			ps.setString(3, client.getAddress());
			ps.setString(4, client.getBankAccount());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 删除客户信息
	 * 
	 * @param name
	 * @param phone
	 * @return
	 */
	public boolean deleClient(String name, String phone) {
		con = SqlConnection.getConnection();
		String sql = "delete from client where cname=? and cphone=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;

	}

	/**
	 * 客户信息修改
	 * 
	 * @param client
	 * @return
	 */
	public boolean updateClient(ClientBean client) {

		con = SqlConnection.getConnection();
		String sql = "update client set cname=?,cphone=?,caddress=?,cbankAccount=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, client.getClientName());
			ps.setString(2, client.getPhoneNumber());
			ps.setString(3, client.getAddress());
			ps.setString(4, client.getBankAccount());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 根据手机号查找客户
	 * @param phone
	 * @return
	 */
	public ClientBean searchClientByPhone(String phone){
		
		con = SqlConnection.getConnection();
		String sql = "select * from client where cphone=?";
		ClientBean client = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,phone);
			rs = ps.executeQuery();
			if(rs.next()){
				client = new ClientBean();
				client.setClientName(rs.getString("cname"));
				client.setPhoneNumber(rs.getString("cphone"));
				client.setAddress(rs.getString("caddress"));
				client.setBankAccount(rs.getString("cbankAccount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	/**
	 * 查看所有客户信息
	 * 
	 * @return
	 */

	public List<ClientBean> searchClient() {
        
		List<ClientBean> list = new ArrayList<ClientBean>();
		con = SqlConnection.getConnection();
		String sql = "select * from client";
		ClientBean client = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				client = new ClientBean();
				client.setClientId(rs.getInt("id"));
				client.setClientName(rs.getString("cname"));
				client.setPhoneNumber(rs.getString("cphone"));
				client.setAddress(rs.getString("caddress"));
				client.setBankAccount(rs.getString("cbankAccount"));
				System.out.println(client.getClientName());
				list.add(client);
				System.out.println(list);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 添加供应商信息
	 * 
	 * @param supplier
	 * @return
	 */
	public boolean addSupplier(SupplierBean supplier) {
		con = SqlConnection.getConnection();
		String sql = "insert int o supplier(sname,sphone,saddress,sbankAccount) values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, supplier.getSname());
			ps.setString(2, supplier.getSphone());
			ps.setString(3, supplier.getSaddress());
			ps.setString(4, supplier.getSbankAccount());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 删除供应商信息
	 * 
	 * @param name
	 * @param phone
	 * @return
	 */
	public boolean deleSupplier(String name, String phone) {

		con = SqlConnection.getConnection();
		String sql = "delete from supplier where sname= ? and phone=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 修改供应商信息
	 * 
	 * @param supplier
	 * @return
	 */
	public boolean updateSupplier(SupplierBean supplier) {

		con = SqlConnection.getConnection();
		String sql = "update client set sname=?,sphone=?,saddress=?,sbankAccount=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, supplier.getSname());
			ps.setString(2, supplier.getSphone());
			ps.setString(3, supplier.getSaddress());
			ps.setString(4, supplier.getSbankAccount());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * 根据手机号查询供应商
	 * @param phone
	 * @return
	 */
	public SupplierBean searchSupplierByPhone(String phone){
		
		con = SqlConnection.getConnection();
		SupplierBean supplier = null;
		String sql = "select * from supplier where sphone=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				supplier = new SupplierBean();
				supplier.setSname(rs.getString("sname"));
				supplier.setSphone(rs.getString("sphone"));
				supplier.setSaddress(rs.getString("saddress"));
				supplier.setSbankAccount(rs.getString("sbankAccount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return supplier;
	}

	/**
	 * 查看供应商信息
	 */
	public List<SupplierBean> searchSupplier() {
        
		List<SupplierBean> list = new ArrayList<SupplierBean>();
		con = SqlConnection.getConnection();
		SupplierBean supplier = null;
		String sql = "select * from supplier";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				supplier = new SupplierBean();
				supplier.setS_id(rs.getInt("id"));
				supplier.setSname(rs.getString("sname"));
				supplier.setSphone(rs.getString("sphone"));
				supplier.setSaddress(rs.getString("saddress"));
				supplier.setSbankAccount(rs.getString("sbankAccount"));
				list.add(supplier);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
