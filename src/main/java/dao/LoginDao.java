package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LoginDao {

	public static boolean validate(String name, String pass) {
		boolean status = false;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/javadark";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "javadark";
		String password = "javadark";

		try(Connection conn = (Connection) DriverManager.getConnection(url, userName, password)) {
			Class.forName(driver).newInstance();
			
			pst = (PreparedStatement) conn
					.prepareStatement("select * from Users where login=? and password=?;");
			pst.setString(1, name);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);

		}
		return status;
	}
}