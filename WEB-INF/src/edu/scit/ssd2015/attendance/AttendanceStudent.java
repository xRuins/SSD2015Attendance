package edu.scit.ssd2015.attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceStudent {
	private int id;
	private int attendanceID;
	private int studentID;
	private int present;
	
	public AttendanceStudent() {
	}

	public AttendanceStudent(int id, int attendanceID, int studentID, int present) {
		this.setID(id);
		this.setAttendanceID(attendanceID);
		this.setStudentID(studentID);
		this.setPresent(present);
	}
	
	public AttendanceStudent(int attendanceID, int studentID, int present) {
		this.setAttendanceID(attendanceID);
		this.setStudentID(studentID);
		this.setPresent(present);
	}
	
	/**
	 * get attendance which have specified id
	 * @param id
	 * @return attendance
	 */
	public static AttendanceStudent getAttendanceStudentByID(int id) {
		AttendanceStudent attendanceStudent = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from attendance_student where id = " + id);
		
		try {
			if (result.next()) {
				int _id = result.getInt("id");
				int attendanceID = result.getInt("attendance_id");
				int studentID = result.getInt("student_id");
				int present = result.getInt("present");
				attendanceStudent = new AttendanceStudent(_id, attendanceID, studentID, present);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return attendanceStudent;
	}
	/**
	 * get AttendanceStudent which have id specified in argument
	 * @param id
	 * @return ArrayList<AttendanceStudent>
	 */
	public static ArrayList<AttendanceStudent> getAttendancesByCourseID(int id) {
		// initialize
		ArrayList<AttendanceStudent> list = new ArrayList<AttendanceStudent>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// operate SQL query
		ResultSet result = dbHelper.query("select * from attendance_student where id = " + id);

		// Iterate result and add the data to array list
		try {
			while (result.next()) {
				int _id = result.getInt("id");
				int attendanceID = result.getInt("attendance_id");
				int studentID = result.getInt("student_id");
				list.add(new AttendanceStudent(_id, attendanceID, studentID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return list;
	}
	
	public Attendance getAttendance() {
		return Attendance.getAttendanceByID(getAttendanceID());
	}
	
	public User getStudent() {
		return User.getUserByID(getStudentID());
	}
	
	/**
	 * save Course to Database.
	 * If the Course exists, create it.
	 * Unless the Course will be updated.
	 */
	public boolean save() {
		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( AttendanceStudent.getAttendanceStudentByID(this.id) == null ) {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into attendance_student (attendance_id, student_id, present)"
						+ "values(?, ?, ?);");
				ps.setInt(1, getAttendanceID());
				ps.setInt(2, getStudentID());
				ps.setInt(3, getPresent());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert AttendanceStudent with: " + e);
				return false;
			} finally {
			    System.out.println("Executed to" + getAttendanceID() + "," + getStudentID() + "," + getPresent());
				dbHelper.close();
			}
			return true;
		}
		// update if the data has same id exists
		else {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"update attendance_student set attendance_id='?', student_id='?', present='?' where id = ?;");
				ps.setInt(1, getAttendanceID());
				ps.setInt(2, getStudentID());
				ps.setInt(3, getPresent());
				ps.setInt(4, getID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update AttendanceStudent with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getAttendanceID() {
		return attendanceID;
	}

	public void setAttendanceID(int attendanceID) {
		this.attendanceID = attendanceID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getPresent() {
		return present;
	}

	public void setPresent(int present) {
		this.present = present;
	}
}
