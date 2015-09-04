package edu.scit.ssd2015.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Semester {
	private int semesterID = -1;
	private String name;
	private int programmeID;
	
	public Semester() {
	}

	public Semester(String name, int programmeID) {
		this.setName(name);
		this.setProgrammeID(programmeID);
	}

	public Semester(int semesterID, String name, int programmeID) {
		this.setSemesterID(semesterID);
		this.setName(name);
		this.setProgrammeID(programmeID);
	}

	public static ArrayList<Semester> getSemesterByProgrammeID(int programmeID) {
		Semester semester = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from semester where programme_id = " + programmeID + ";");
		ArrayList<Semester> semesters = getArrayListFromResultSet(result); 
		dbHelper.close();
		return semesters;
	}
	
	public static Semester getSemesterByID(int semesterID) {
		Semester semester = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from semester where id = " + semesterID + ";");
		ArrayList<Semester> semesters = getArrayListFromResultSet(result); 
		if (!(semesters.isEmpty())) {
			semester = semesters.get(0);
		}
		dbHelper.close();
		return semester;
	}
	
	private static ArrayList<Semester> getArrayListFromResultSet(ResultSet rs) {
		ArrayList<Semester> result = new ArrayList<Semester>();
		try {
			while(rs.next()) {
				int _semesterID = rs.getInt("semester_id");
				String name = rs.getString("name");
				int programmeID = rs.getInt("programme_id");
				result.add(new Semester(_semesterID, name, programmeID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Programme getProgramme() {
		return Programme.getProgrammeByID(getProgrammeID());
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getSemesterID() == -1 || Semester.getSemesterByID(getSemesterID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into semester (name, programme_id) values(?, ?);"
						);
				ps.setString(1, getName());
				ps.setInt(2, getProgrammeID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert semester with: " + e);
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
						"update semester set name = ?, batch_id = ? where programme_id = ?;");
				ps.setString(1, getName());
				ps.setInt(2, getProgrammeID());
				ps.setInt(3, getSemesterID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update Batch with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}	
	}

	public int getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProgrammeID() {
		return programmeID;
	}

	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}
}
