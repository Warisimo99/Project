import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector extends DBCredentials {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			throw new SQLException("Driver Not Found");
		}
		
		String connectURL = "jdbc:mysql://localhost:3306/2111015db";
		
		return DriverManager.getConnection(connectURL, username, password);
	}
	
}
