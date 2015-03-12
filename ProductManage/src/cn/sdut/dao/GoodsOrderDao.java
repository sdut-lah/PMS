package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.sdut.entity.GoodsOrderBean;
import cn.sdut.util.SqlConnection;

public class GoodsOrderDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<GoodsOrderBean> list = null;
    /**
     * 下订货单
     * @param goodsOrder
     * @return
     */
	public int makeGoodsOrder(GoodsOrderBean goodsOrder) {

		int orderId = 0;
		con = SqlConnection.getConnection();
		String sql = "insert into goods_order(gname,gnumber,gprice,operrater,supplier,state,gtime) values(?,?,?,?,?,?,?)";
		String sqlStr = "select max(id) from goods_order";
		try {
			Statement st = con.createStatement();
			ps = con.prepareStatement(sql);
			ps.setString(1, goodsOrder.getGname());
			ps.setInt(2, goodsOrder.getGnumber());
			ps.setDouble(3, goodsOrder.getGprice());
			ps.setString(4, goodsOrder.getOperater());
			ps.setString(5,goodsOrder.getGsupplier());
			ps.setInt(6, goodsOrder.getState());
			ps.setString(7, goodsOrder.getGtime());
			ps.executeUpdate();

			rs = st.executeQuery(sqlStr);
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderId;
	}
	/**
	 * 查找为入库的已完成订单
	 * @return
	 */
	public List<GoodsOrderBean> searchAllOrder(){
		
		list = new ArrayList<GoodsOrderBean>();
		GoodsOrderBean gob = null;
		con = SqlConnection.getConnection();
		String sql = "select * from Goods_order where state=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 0);
			rs = ps.executeQuery();
			while(rs.next()){
				gob = new GoodsOrderBean();
				gob.setG_id(rs.getInt("id"));
				gob.setGname(rs.getString("gname"));
				gob.setGnumber(rs.getInt("gnumber"));
				gob.setGprice(rs.getDouble("gprice"));
				gob.setOperater(rs.getString("operrater"));
				gob.setGsupplier(rs.getString("supplier"));
				gob.setState(rs.getInt("state"));
				gob.setGtime(rs.getString("gtime"));
				list.add(gob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 改变订单状态
	 * @param id
	 */
   public void changState(int id){
		
		con = SqlConnection.getConnection();
		String sql = "update goods_order set state=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
