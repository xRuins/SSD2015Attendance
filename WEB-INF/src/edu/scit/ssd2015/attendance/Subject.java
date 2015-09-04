package edu.scit.ssd2015.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Subject {
	private int subjectID = -1;
	private String name;
	private int semesterID;
	
	public Subject() {
	}

	public Subject(String name, int semesterID) {
		this.setName(name);
		this.setSemesterID(semesterID);
	}

	public Subject(int subjectID, String name, int semesterID) {
		this.setSubjectID(subjectID);
		this.setName(name);
		this.setSemesterID(semesterID);
	}

	public static ArrayList<Subject> getSubjectBySemesterID(int semesterID) {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from subject where semester_id = " + semesterID + ";");
		try {
			while(result.next()) {
				int _subjectID = result.getInt("subject_id");
				String name = result.getString("name");
				subjects.add(new Subject(_subjectID, name, semesterID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return subjects;
	}
	
	public static Subject getSubjectByID(int subjectID) {
		Subject subject = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from subject where subject_id = " + subjectID + ";");
		ArrayList<Subject> subjects = Subject.getArrayListFromResultSet(result); 
		if (!(subjects.isEmpty())) {
			subject = subjects.get(0);
		}
		dbHelper.close();
		return subject;
	}
	
	private static ArrayList<Subject> getArrayListFromResultSet(ResultSet rs) {
		ArrayList<Subject> result = new ArrayList<Subject>();
		try {
			while(rs.next()) {
				int _subjectID = rs.getInt("subject_id");
				String name = rs.getString("name");
				int semesterID = rs.getInt("semester_id");
				result.add(new Subject(_subjectID, name, semesterID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Semester getSemester() {
		return Semester.getSemesterByID(getSemesterID());
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getSubjectID() == -1 || Subject.getSubjectByID(getSubjectID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into subject (name, semester_id) values(?, ?);"
						);
				ps.setString(1, getName());
				ps.setInt(2, getSemesterID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert division with: " + e);
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
						"update subject set name = ?, semester_id = ? where subject_id = ?;");
				ps.setString(1, getName());
				ps.setInt(2, getSemesterID());
				ps.setInt(3, getSubjectID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update Division with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}	
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(int semesterID) {
		this.semesterID = semesterID;
	}

}
