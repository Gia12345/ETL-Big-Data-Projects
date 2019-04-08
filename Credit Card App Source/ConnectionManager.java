package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	private static String url = "jdbc:mysql://localhost:3306/cdw_sapp";
	private static String username = "root";
	private static String password = "root";
	private static Connection con = null;
	
	public static Connection getConnection() {
		con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful!\n");
		} catch (SQLException e) {
			OutputFormatter.printMessage("Database connection NOT successful!");
		}
		return con;
	}
	public static void closeObjects(ResultSet rs, PreparedStatement ps, Connection c) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		} catch (SQLException e)  {
			System.out.println("Error closing database connection.");
		}
	}
}
