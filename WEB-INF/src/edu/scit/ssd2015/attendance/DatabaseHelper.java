package edu.scit.ssd2015.attendance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
	
	private static final String databaseAddress = "jdbc:mysql://localhost:3306/scit_attendance2";
	private static final String databaseUser = "root";
	private static final String databasePassword = "";
	public Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public DatabaseHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(databaseAddress, databaseUser, databasePassword);
            statement = connection.createStatement();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver does not exists " + e);
		} catch (SQLException e) {
			System.out.println("Error happened when connect to MySQL server" + e);
		}
	}
	
	public ResultSet query(String q) {
		try {
            resultSet = statement.executeQuery(q);
            return resultSet;
		} catch (SQLException e) {
			System.out.println("The query was failed by" + e);
			return null;
		}
	}
	
	public void close() {
		try {
			if ( resultSet != null ) {
				resultSet.close();
	        	statement.close();
	        	connection.close();
			}
		} catch (SQLException e) {
			System.out.println("MySQL connection couldn't be disconnected by "+ e);
		}
	}
}
