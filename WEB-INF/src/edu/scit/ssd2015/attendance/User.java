package edu.scit.ssd2015.attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int id = -1;
	private String name;
	private String userID;
	private String password;
	private String email;
	private int userType;
	private int programmeID;
	private int batchID;
	
	public User() {
	}
	
	public User(int id, String name, String email, String userID,
				String password, int userType, int programmeID, int batchID) {
		this.setID(id);
		this.setName(name);
		this.setEmail(email);
		this.setUserID(userID);
		this.setUserType(userType);
		this.setPassword(password);
		this.setProgrammeID(programmeID);
		this.setBatchID(batchID);
	}
	
	public User(String name, String email, String userID,
				String password, int userType, int programmeID, int batchID) {
		this.setName(name);
		this.setEmail(email);
		this.setUserID(userID);
		this.setUserType(userType);
		this.setPassword(password);
		this.setProgrammeID(programmeID);
		this.setBatchID(batchID);
	}
	
	public static User getUserByID(int id) {
		User user = new User();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from user where id = " + id + ";");

		try {
			result.next();
			String name = result.getString("name");
			String email = result.getString("email");
			String userID = result.getString("user_id");
			String password = result.getString("password");
			int userType = result.getInt("user_type");
			int programmeID = result.getInt("programme_id");
			int batchID = result.getInt("batch_id");
			
			user = new User(id, name, email, userID, password, userType, programmeID, batchID);
			System.out.println("User found:" + id + userID + name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return user;
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getID() == -1 || User.getUserByID(getID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into user (user_id, password, name, user_type, programme_id, batch_id, email)"
						+ "values(?, ?, ?, ?, ?, ?, ?);");
				ps.setString(1, getUserID());
				ps.setString(2, getPassword());
				ps.setString(3, getName());
				ps.setInt(4, getUserType());
				ps.setInt(5, getProgrammeID());
				ps.setInt(6, getBatchID());
				ps.setString(7, getEmail());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert User with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}
		// update if the data has same id exists
		else {
			try {
				System.out.println("Start to update");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"update user set user_id=?,password=?,name=?,user_type=?,"
						+ "programme_id=?,batch_id=?,email=? where id=?;");
				ps.setString(1, getUserID());
				ps.setString(2, getPassword());
				ps.setString(3, getName());
				ps.setInt(4, getUserType());
				ps.setInt(5, getProgrammeID());
				ps.setInt(6, getBatchID());
				ps.setString(7, getEmail());
				ps.setInt(8, getID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update User with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getProgrammeID() {
		return programmeID;
	}

	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
