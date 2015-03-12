package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.sdut.entity.AdminBean;
import cn.sdut.entity.OperaterBean;
import cn.sdut.util.SqlConnection;

public class LoginDao {

	PreparedStatement ps = null;
	Connection con = null;

	/**
	 * 根据id查找操作员
	 * @param id
	 * @return
	 */
	public OperaterBean getOperaterByID(int id) {
		con = SqlConnection.getConnection();
		String sqlStr = "select * from Operater where id=?";
		OperaterBean operater = null;

		try {
			ps = con.prepareStatement(sqlStr);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				operater = new OperaterBean();
				operater.setName(rs.getString("name"));
				operater.setPsw(rs.getString("password"));

			}

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

		return operater;
	}

	/**
	 * 根据id查找管理员
	 * @param id
	 * @return
	 */
	public AdminBean getAdminByID(int id) {

		con = SqlConnection.getConnection();
		String sqlStr = "select * from admin where id=?";
		AdminBean admin = null;

		try {
			ps = con.prepareStatement(sqlStr);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin = new AdminBean();
				admin.setAd_id(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setPsw(rs.getString("password"));
			}
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;
	}

}
