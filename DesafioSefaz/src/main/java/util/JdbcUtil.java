package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	
	public static Connection getConnection() throws SQLException {

		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "LUCAS", "LUCAS");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	
}
