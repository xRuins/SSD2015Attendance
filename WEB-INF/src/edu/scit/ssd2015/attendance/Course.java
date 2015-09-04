package edu.scit.ssd2015.attendance;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
	private int courseID;
	private int divisionID;
	private int subjectID;
	
	public Course() {
	}
	
	public Course(int divisionID, int subjectID) {
		this.setDivisionID(divisionID);
		this.setSubjectID(subjectID);
	}
	
	public Course(int courseID, int divisionID, int subjectID) {
		this.setCourseID(courseID);
		this.setDivisionID(divisionID);
		this.setSubjectID(subjectID);
	}

	/**
	 * get course who has specified ID
	 * @param id
	 * @return Course
	 */
	public static Course getCourseByID(int id) {
		Course course = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from course where course_id = " + id);
		
		try {
			// check any data exists in ResultSet
			if (result.next()) {
				int courseID = result.getInt("course_id");
				int divisionID = result.getInt("division_id");
				int subjectID = result.getInt("subject_id");	
				course = new Course(courseID, divisionID, subjectID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return course;
	}
	/**
	 * get course who has specified divisionID and subjectID
	 * @param divisionID, subjectID
	 * @return Course
	 */
	public static Course getCourseByIDs(int divisionID, int subjectID) {
		Course course = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from course where division_id=" + divisionID + 
				" and subject_id=" + subjectID + ";");
		
		try {
			// check any data exists in ResultSet
			if (result.next()) {
				int courseID = result.getInt("course_id");
				course = new Course(courseID, divisionID, subjectID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return course;
	}

	
	public ArrayList<User> getStudent() {
		ArrayList<User> students = new ArrayList<>();
		ArrayList<AttendanceStudent> attendanceStudents = new ArrayList<>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		int courseID = getCourseID();
		ResultSet result = dbHelper.query("select * from course_student where course_id = " + courseID);
		try {
			// make set of AttendanceStudent
			while (result.next()) {
				int id = result.getInt("id");
				int studentID = result.getInt("student_id");
				System.out.println("[CS]" + id);
				attendanceStudents.add(new AttendanceStudent(id, studentID, courseID));
			}
			
			// make set of User
			for (AttendanceStudent as: attendanceStudents) {
				System.out.println("[CrsGetStd]" +  as.getStudentID());
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
	 * get attendance which related to specified course
	 * @param id
	 * @return ArrayList of Attendances
	 */
	public static ArrayList<Attendance> getAttendances(int id) {
		return Attendance.getAttendancesByCourseID(id);
	}
	
	public Subject getSubject() {
		return Subject.getSubjectByID(getSubjectID());
	}
	public Division getDivision() {
		return Division.getDivisionByID(getDivisionID());
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(int divisionID) {
		this.divisionID = divisionID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	
	public String getCourseName() {
		String subjectName = getSubject().getName();
		String divisionName = getDivision().getName();
		return subjectName + " : " + divisionName;
	}
}
