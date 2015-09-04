package edu.scit.ssd2015.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Programme {
	private int programmeID = -1;
	private String name;
	
	public Programme() {
	}

	public Programme(String name) {
		this.setName(name);
	}

	public Programme(int programmeID, String name) {
		this.setProgrammeID(programmeID);
		this.setName(name);
	}
	
	public static ArrayList<Programme> getAllProgramme() {
		ArrayList<Programme> programmes = new ArrayList<Programme>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from programme;");

		try {
			while(result.next()) {
				int _programmeID = result.getInt("programme_id");
				String name = result.getString("name");
			
				programmes.add(new Programme(_programmeID, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return programmes;
	}
	
	public static Programme getProgrammeByID(int programmeID) {
		Programme programme = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from programme where id = " + programmeID + ";");

		try {
			result.next();
			 int _programmeID = result.getInt("programme_id");
			String name = result.getString("name");
			
			programme = new Programme(_programmeID, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return programme;
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getProgrammeID() == -1 || Programme.getProgrammeByID(getProgrammeID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into user (name) values(?);"
						);
				ps.setString(1, getName());
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
						"update programme set name = ? where programme_id= ?;");
				ps.setString(1, getName());
				ps.setInt(2, getProgrammeID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update Programme with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}	
	}

	public int getProgrammeID() {
		return programmeID;
	}

	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
