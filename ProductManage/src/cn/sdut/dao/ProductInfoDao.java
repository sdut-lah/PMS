package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.sdut.entity.ProductBean;
import cn.sdut.util.SqlConnection;

public class ProductInfoDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<ProductBean> list = null;

	/**
	 * 增加产品信息,返回产品id
	 * 
	 * @param product
	 * @return
	 */
	public int addProduct(ProductBean product) {

		con = SqlConnection.getConnection();
		String sqlInsert = "insert into productInfo(pname,pnumber,price,supplier,date) values(?,?,?,?,?)";
		String sqlStr = "select max(id) from productInfo";

		try {
			Statement st = con.createStatement();
			ps = con.prepareStatement(sqlInsert);

			ps.setString(1, product.getPname());
			ps.setInt(2, product.getPnumber());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getSupplier());
			ps.setString(5, product.getStockInTime());

			ps.executeUpdate();

			rs = st.executeQuery(sqlStr);
			if (rs.next()) {
				int id = rs.getInt(1);
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

	/**
	 * 根据id删除产品信息
	 */
	public boolean deleProductById(int id) {

		con = SqlConnection.getConnection();
		String sqlDele = "delete from productInfo where id=?";
		try {
			ps = con.prepareStatement(sqlDele);
			ps.setInt(1, id);
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
	 * 根据name删除产品信息
	 */
	public boolean deleProductByName(String name) {

		con = SqlConnection.getConnection();
		String sqlDele = "delete from productInfo where pname=?";
		try {
			ps = con.prepareStatement(sqlDele);
			ps.setString(1, name);
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
	 * 修改产品信息
	 */

	public boolean updateProduct(ProductBean product) {

		con = SqlConnection.getConnection();
		String sqlUpdate = "update productInfo set pname=?,price=?,pnumber=?,supplier=?,date=?";
		try {
			ps = con.prepareStatement(sqlUpdate);
			ps.setString(1, product.getPname());
			ps.setDouble(2, product.getPrice());
			ps.setInt(3, product.getPnumber());
			ps.setString(4, product.getSupplier());
			ps.setString(5, product.getStockInTime());
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
	 * 根据id查看产品信息
	 */
	public ProductBean searchById(int id) {

		con = SqlConnection.getConnection();
		String sqlQuery = "select * from productInfo where id = ?";
		ProductBean product = null;
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			product = new ProductBean();
			while (rs.next()) {
				product.setP_id(rs.getInt("id"));
				product.setPname(rs.getString("pname"));
				product.setPrice(rs.getDouble("price"));
				product.setPnumber(rs.getInt("pnumber"));
				product.setSupplier(rs.getString("supplier"));
				product.setStockInTime(rs.getString("date"));
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
	 * 根据name查看商品信息
	 */
	public ProductBean searchByName(String name) {

		con = SqlConnection.getConnection();
		String sqlQuery = "select * from productInfo where pname = ? order by pnumber";
		ProductBean product = null;
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, name);
			rs = ps.executeQuery();
			product = new ProductBean();
			while (rs.next()) {
				product.setP_id(rs.getInt("id"));
				product.setPname(rs.getString("Pname"));
				product.setPrice(rs.getDouble("price"));
				product.setPnumber(rs.getInt("pnumber"));
				product.setStockInTime(rs.getString("date"));
				product.setSupplier(rs.getString("supplier"));
			}
			return product;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * 查看所有商品的信息
	 */
	public List<ProductBean> searchAll() {

		con = SqlConnection.getConnection();
		list = new ArrayList<ProductBean>();
		String sqlStr = "select * from productInfo";
		ProductBean product = null;
		try {
			ps = con.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductBean();
				product.setP_id(rs.getInt("id"));
				product.setPname(rs.getString("pname"));
				product.setPrice(rs.getDouble("price"));
				product.setPnumber(rs.getInt("pnumber"));
				product.setStockInTime(rs.getString("date"));
				product.setSupplier(rs.getString("supplier"));
				list.add(product);
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
	 * 按库存量排序
	 * 
	 * @return
	 */
	public List<ProductBean> sortProductByNum() {

		list = new ArrayList<ProductBean>();
		con = SqlConnection.getConnection();
		String sql = "select * from productInfo order by pnumber";
		ProductBean product = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new ProductBean();
				product.setP_id(rs.getInt("id"));
				product.setPname(rs.getString("pname"));
				product.setPrice(rs.getDouble("price"));
				product.setPnumber(rs.getInt("pnumber"));
				product.setStockInTime(rs.getString("date"));
				product.setSupplier(rs.getString("supplier"));
				list.add(product);
			}
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
		return list;
	}

	/**
	 * 价格调整
	 * 
	 * @param price
	 * @return
	 */
	public boolean changePrice(double price) {

		con = SqlConnection.getConnection();
		String sql = "update productInfo set price=price+?";
		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, price);
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

}
