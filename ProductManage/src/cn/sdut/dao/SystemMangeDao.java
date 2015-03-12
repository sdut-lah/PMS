package cn.sdut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.sdut.entity.OperaterBean;
import cn.sdut.util.SqlConnection;

public class SystemMangeDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * 添加操作员
	 * 
	 * @param operater
	 * @return
	 */
	public int addOperater(OperaterBean operater) {

		con = SqlConnection.getConnection();
		String sql = "insert into operater(name,password) values(?,?)";
		String sqlStr = "select max(id) from operater";
		try {
			Statement st = con.createStatement();
			ps = con.prepareStatement(sql);
			ps.setString(1, operater.getName());
			ps.setString(2, operater.getPsw());
			ps.executeUpdate();
			
			rs = st.executeQuery(sqlStr);
			int id = 0;
			if(rs.next()){
			   id = rs.getInt(1);
			}
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
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
     * 删除操作员
     * @param name
     * @param id
     * @return
     */
	public boolean deleOperater(String name, String id) {

		con = SqlConnection.getConnection();
		String sql = "delete from operater where id=? and name=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
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
	 * 修改密码
	 * @param id
	 * @param newPwd
	 * @param flag
	 * @return
	 */
	public boolean changePwd(int id,String newPwd,int flag){
		
		con = SqlConnection.getConnection();
		String sql = null;
		if(flag==0){
		  sql = "update Operater set password=? where id=?";
		}
		else{
			sql = "update admin set password=? where id=?";
		}
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newPwd);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
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
