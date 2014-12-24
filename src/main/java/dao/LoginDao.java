package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LoginDao {

	static boolean status = false;
	static Connection conn = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	public static Connection dbConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/JavaDark";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "Bdgolk@82";

		Class.forName(driver).newInstance();
		conn = (Connection) DriverManager
				.getConnection(url, userName, password);

		return conn;
	}

	// Method check if user and password exist in DB and match each other
	public static boolean validate(String name, String pass) {

		try {

			conn = dbConnection();
			pst = (PreparedStatement) conn
					.prepareStatement("select * from Users where login=? and password=?;");
			pst.setString(1, name);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}

	//Check if entered user name id already exist in DB
	public static boolean checkUser(String user) {
		try {
			conn = dbConnection();
			pst = (PreparedStatement) conn
					.prepareStatement("select * from Users where login=?;");
			pst.setString(1, user);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}
	
	//Check if entered email name id already exist in DB
		public static boolean checkEmail(String email) {
			try {
				conn = dbConnection();
				pst = (PreparedStatement) conn
						.prepareStatement("select * from Users where email=?;");
				pst.setString(1, email);

				rs = pst.executeQuery();
				status = rs.next();

			} catch (Exception e) {
				System.out.println(e);

			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return status;
		}
}