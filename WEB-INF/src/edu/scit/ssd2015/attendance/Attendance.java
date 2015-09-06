package edu.scit.ssd2015.attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Attendance {
	private int attendanceID;
	private java.sql.Date attendanceDate;
	private int courseID;
	private int userID;
	
	public Attendance() {
	}
	
	public Attendance(int attendanceID, Date attendanceDate, int courseID, int userID) {
		this.setAttendanceID(attendanceID);
		this.setCourseID(courseID);
		this.setAttendanceDate(attendanceDate);
		this.setUserID(userID);
	}
	
	public Attendance(Date attendanceDate, int courseID, int userID) {
		this.setAttendanceDate(attendanceDate);
		this.setCourseID(courseID);
		this.setUserID(userID);
	}
	
	public static ArrayList<Attendance> getAttendanceByCourseID (int courseID) {
		ArrayList<Attendance> attendances = new ArrayList<>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from attendance where course_id = " + courseID);
		
		try {
			// check any data exists in ResultSet
			while (result.next()) {
				int userID = result.getInt("user_id");
				Date attendanceDate = result.getDate("attendance_date");
				int attendanceID = result.getInt("attendance_id");
				attendances.add(new Attendance(attendanceID, attendanceDate, courseID, userID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return attendances;
	}
	
	public Course getCourse() {
		return Course.getCourseByID(getCourseID());
	}
	
	public ArrayList<User> getStudent() {
		ArrayList<User> students = new ArrayList<>();
		ArrayList<AttendanceStudent> attendanceStudents = new ArrayList<>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from attendance_student where attendance_id = " + getAttendanceID());
		try {
			// make set of AttendanceStudent
			while (result.next()) {
				int id = result.getInt("id");
				int attendanceID = result.getInt("attendance_id");
				int studentID = result.getInt("student_id");
				int present = result.getInt("present");
				attendanceStudents.add(new AttendanceStudent(id, attendanceID, studentID, present));
			}
			
			// make set of User
			for (AttendanceStudent as: attendanceStudents) {
				System.out.println("as:" + as.getID());
				students.add(User.getUserByID(as.getStudentID()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return students;
	}
	
	/**
	 * get attendance which have specified id
	 * @param id
	 * @return attendance
	 */
	public static Attendance getAttendanceByID(int id) {
		Attendance attendance = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from attendance where attendance_id = " + id);
		
		try {
			// check any data exists in ResultSet
			if (result.next()) {
				int userID = result.getInt("user_id");
				Date attendanceDate = result.getDate("attendance_date");
				int courseID = result.getInt("course_id");
				int attendanceID = result.getInt("attendance_id");
				attendance = new Attendance(attendanceID, attendanceDate, courseID, userID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return attendance;
	}
	/**
	 * get Attendances which have course_id specified in argument
	 * @param id
	 * @return ArrayList<Attendance>
	 */
	public static ArrayList<Attendance> getAttendancesByCourseID(int id) {
		// initialize
		ArrayList<Attendance> list = new ArrayList<Attendance>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// operate SQL query
		ResultSet result = dbHelper.query("select * from attendance where course_id = " + id);

		// Iterate result and add the data to array list
		try {
			while (result.next()) {
				int attendanceID = result.getInt("attendance_id");
				Date attendanceDate = result.getDate("attendance_date");
				int userID = result.getInt("user_id");
				int courseID = result.getInt("course_id");
				list.add(new Attendance(attendanceID, attendanceDate, courseID, userID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return list;
	}

	// getter and setter method
	public int getAttendanceID() {
		return attendanceID;
	}

	public void setAttendanceID(int attendanceID) {
		this.attendanceID = attendanceID;
	}
	
	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = new java.sql.Date(attendanceDate.getTime());;
	}
	
	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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
		if ( Attendance.getAttendanceByID(this.attendanceID) == null ) {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into attendance (attendance_date, course_id, user_id)"
						+ "values(?, ?, ?);");
				ps.setDate(1, (java.sql.Date) getAttendanceDate());
				ps.setInt(2, getCourseID());
				ps.setInt(3, getUserID());
			    ps.executeUpdate();
				ResultSet resultLast = dbHelper.query("select LAST_INSERT_ID() AS LAST;");
				resultLast.next();
				int lastInsertedID = resultLast.getInt("LAST");
				System.out.println("[Attendance Save] last inserted: " + lastInsertedID);
				this.setAttendanceID(lastInsertedID);
			} catch (SQLException e) {
				System.out.println("Failed to insert Attendance with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}

			System.out.println("Succeed to insert Attendance" + getAttendanceID());
			return true;
		}
		// update if the data has same id exists
		else {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"update attendance set attendance_date='?', course_id='?', user_id='?' where id = id;");
				ps.setDate(1, (java.sql.Date) getAttendanceDate());
				ps.setInt(2, getCourseID());
				ps.setInt(3, getUserID());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to update Attendance with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			System.out.println("Succeed to update Attendance" + getAttendanceID());
			return true;
		}
	}

	private int getLastInsertID() {
		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select LAST_INSERT_ID();");
		int ret = -1;
		try {
			result.next();
			ret = result.getInt("LAST_INSERT_ID()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbHelper.close();
		}
		System.out.println("LAST_INS_ID: " + ret);
		return ret;
	}
}
