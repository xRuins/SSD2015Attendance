package edu.scit.ssd2015.attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseStudent {
	private int id;
	private int courseID;
	private int studentID;
	
	public CourseStudent() {
	}
	
	public CourseStudent(int id, int courseID, int studentID) {
		this.setID(id);
		this.setCourseID(courseID);
		this.setStudentID(studentID);
	}
	
	/**
	 * get attendance which have specified id
	 * @param id
	 * @return attendance
	 */
	public static CourseStudent getCourseStudentByID(int id) {
		CourseStudent CourseStudent = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from course_student where id = " + id);
		
		try {
			if (result.next()) {
				int _id = result.getInt("id");
				int courseID = result.getInt("course_id");
				int studentID = result.getInt("student_id");
				CourseStudent = new CourseStudent(_id, courseID, studentID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return CourseStudent;
	}
	/**
	 * get CourseStudent which have id specified in argument
	 * @param id
	 * @return ArrayList<CourseStudent>
	 */
	public static ArrayList<CourseStudent> getAttendancesByCourseID(int id) {
		// initialize
		ArrayList<CourseStudent> list = new ArrayList<CourseStudent>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// operate SQL query
		ResultSet result = dbHelper.query("select * from course_student where id = " + id);

		// Iterate result and add the data to array list
		try {
			while (result.next()) {
				int _id = result.getInt("id");
				int courseID = result.getInt("course_id");
				int studentID = result.getInt("student_id");
				list.add(new CourseStudent(_id, courseID, studentID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return list;
	}
	
	public Course getCourse() {
		return Course.getCourseByID(getcourseID());
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
		if ( CourseStudent.getCourseStudentByID(this.id) == null ) {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into course_student (course_id, student_id)"
						+ "values(?, ?);");
				ps.setInt(1, getcourseID());
				ps.setInt(2, getStudentID());
			} catch (SQLException e) {
				System.out.println("Failed to insert Course with: " + e);
				return false;
			} finally {
				dbHelper.close();
			}
			return true;
		}
		// update if the data has same id exists
		else {
			try {
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"update course_student set course_id='?', student_id='?' where id = id;");
				ps.setInt(1, getcourseID());
				ps.setInt(2, getStudentID());
				ps.setInt(3, getID());
			} catch (SQLException e) {
				System.out.println("Failed to update Course with: " + e);
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

	public int getcourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
}
