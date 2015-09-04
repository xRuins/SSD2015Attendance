package edu.scit.ssd2015.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Batch {
	private int batchID = -1;
	private int beginYear;
	private int endYear;
	private int programmeID;
	
	public Batch() {
	}

	public Batch(int batchID, int beginYear, int endYear, int programmeID) {
		this.setBatchID(batchID);
		this.setBeginYear(beginYear);
		this.setEndYear(endYear);
		this.setProgrammeID(programmeID);
	}

	public Batch(int beginYear, int endYear, int programmeID) {
		this.setBeginYear(beginYear);
		this.setEndYear(endYear);
		this.setProgrammeID(programmeID);
	}

	
	public static Batch getBatchByID(int batchID) {
		Batch batch = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from batch where id = " + batchID + ";");

		try {
			result.next();
			int _batchID = result.getInt("batch_id");
			int beginYear = result.getInt("begin_year");
			int endYear = result.getInt("end_year");
			int programmeID = result.getInt("programmeID");
			
			batch = new Batch(_batchID, beginYear, endYear, programmeID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return batch;
	}
	
	public static ArrayList<Batch> getBatchByProgrammeID(int programmeID) {
		ArrayList<Batch> batches = new ArrayList<Batch>();
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from batch where programme_id = " + programmeID + ";");

		try {
			while(result.next()) {
				int _batchID = result.getInt("batch_id");
				System.out.println(_batchID);
				int beginYear = result.getInt("begin_year");
				int endYear = result.getInt("end_year");
			
				batches.add(new Batch(_batchID, beginYear, endYear, programmeID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.close();
		return batches;
	}
	
	public Programme getPrgramme() {
		return Programme.getProgrammeByID(getProgrammeID());
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getBatchID() == -1 || Batch.getBatchByID(getBatchID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into batch (name) values(?, ?);"
						);
				ps.setInt(1, getBeginYear());
				ps.setInt(2, getEndYear());
			    ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Failed to insert batch with: " + e);
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
						"update batch set begin_year = ?, end_year = ? where batch_id = ?;");
				ps.setInt(1, getBeginYear());
				ps.setInt(2, getEndYear());
				ps.setInt(3, getBatchID());
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
	
	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	public String getBatchName() {
		return getBeginYear() + "-" + getEndYear();
	}

	public int getProgrammeID() {
		return programmeID;
	}

	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}
}
