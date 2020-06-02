package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Alessandro Capici
 * @author Cristian Garau
 * @author Andrea
 *
 */

public class RdbOperation {
	private Connection c;
	private Statement stmt;

	public RdbOperation() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Util/projectDatabase.sqlite");
			System.out.println("Connection done");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}

	public Connection getC() {
		return c;
	}

	public Statement getStmt() {
		return stmt;
	}

	/*
	 * QUERY COMPONENT RELATION
	 */
	public ResultSet getAllComponents() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT c.TypeofC,Model,Price,Name,AttValue\r\n"
					+ "FROM Component as c join Attribute \r\n" + "WHERE c.Model=ModelofC");

			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;

	}

	public boolean removeComponent(String model, String type) {
		String sql = "DELETE FROM Component WHERE TypeofC = ? AND Model= ?";

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, type);
			pstmt.setString(2, model);
			// execute the delete statement
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}
	
	/*
	 * QUERY ATTRIBUTE RELATION
	 */
	public ResultSet getAttributeByComponent(String model, String typeOfComponet) {
		String sql = "select * " + 
					"from Attribute " + 
					"where TypeofC = '"+ typeOfComponet +"' and ModelofC = '"+ model +"'";
		
		ResultSet rs = null;
		try {
		
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error: "+ e.getMessage());
		}
		
		return rs;		
		
	}

	public ResultSet getConfiguration(String confId) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *\r\n" + "FROM Configuration\r\n" + "WHERE id='" + confId + "'");
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public ResultSet getConfigurationByEmail(String email) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT *\r\n" + "FROM Configuration\r\n" + "WHERE EmailU='" + email + "'");
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public boolean addUser(String name,String cognome, String email,String password) {
		String sql = "INSERT INTO Users(firstName,lastName,email,password) VALUES(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, cognome);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean addConfiguration(int id, String name, String email) {
		String sql = "INSERT INTO Configuration(Id,Name,EmailU) VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean updateConfiguration(int id, String name, String email) {

		return false;

	}

	public boolean removeConfiguration(int id) {

		return false;

	}
	
	public ResultSet getAllConstraints() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bound");
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
		
	}
}
