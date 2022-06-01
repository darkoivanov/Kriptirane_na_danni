package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sql {
	private Connection connection;
	private Connector connect;
	
	public Sql() {
		this.connection = connect.getConnectionInstance().getConnection();
	}
	
	public boolean InsertEncryptedData(String data) {
		String query = "INSERT INTO users(data) VALUES (?)";
		
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(query);
			pstmt.setString(1, data);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String SelectEncryptedData() {
		String data = null;
		String query = "SELECT * FROM users";
		
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = rs.getString("data");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return data;
	}
	
	public String SelectByData(String chkData) {
		String data = null;
		String query = "SELECT * FROM users WHERE data = ?";
		
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(query);
			pstmt.setString(1, chkData);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = rs.getString("data");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return data;
	}
	
	public boolean DeleteEncryptData() {
		String query = "DELETE FROM users";
		
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(query);
			
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
}
