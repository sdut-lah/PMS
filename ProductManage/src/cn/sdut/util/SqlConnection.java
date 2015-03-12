package cn.sdut.util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class SqlConnection {

	private static String url = "jdbc:mysql://localhost/PMS";
	private static String user = "root";
	private static String psw = "ab123456";
	private static Connection con = null;
   
	/**
	 * 连接数据库
	 * @return
	 */
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, psw);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败....");
		}
		return con;
	}

}
