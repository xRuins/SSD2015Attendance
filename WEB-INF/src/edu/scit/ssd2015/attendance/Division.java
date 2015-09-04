package edu.scit.ssd2015.attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Division {
	private int divisionID = -1;
	private String name;
	private int batchID;
	
	public Division() {
	}

	public Division(String name, int batchID) {
		this.setName(name);
		this.setBatchID(batchID);
	}

	public Division(int divisionID, String name, int batchID) {
		this.setDivisionID(divisionID);
		this.setName(name);
		this.setBatchID(batchID);
	}

	public static ArrayList<Division> getDivisionByBatchID(int batchID) {
		Division division = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from division where batch_id = " + batchID + ";");
		ArrayList<Division> divisions = Division.getArrayListFromResultSet(result); 
		dbHelper.close();
		return divisions;
	}
	
	public static Division getDivisionByID(int divisionID) {
		Division division = null;
		DatabaseHelper dbHelper = new DatabaseHelper();
		ResultSet result = dbHelper.query("select * from division where division_id = " + divisionID + ";");
		ArrayList<Division> divisions = Division.getArrayListFromResultSet(result); 
		if (!(divisions.isEmpty())) {
			division = divisions.get(0);
		}
		dbHelper.close();
		return division;
	}
	
	private static ArrayList<Division> getArrayListFromResultSet(ResultSet rs) {
		ArrayList<Division> result = new ArrayList<Division>();
		try {
			while(rs.next()) {
				int _divisionID = rs.getInt("division_id");
				String name = rs.getString("name");
				int batchID = rs.getInt("batch_id");
				result.add(new Division(_divisionID, name, batchID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Batch getBatch() {
		return Batch.getBatchByID(getBatchID());
	}
	
	public Boolean save() {

		// initialize database helper
		DatabaseHelper dbHelper = new DatabaseHelper();
		
		// insert if the data has same id does not exist
		if ( this.getDivisionID() == -1 || Division.getDivisionByID(getDivisionID()) == null ) {
			try {
				System.out.println("Start to create");
				PreparedStatement ps = dbHelper.connection.prepareStatement(
						"insert into division (name, batch_id) values(?, ?);"
						);
				ps.setString(1, getName());
				ps.setInt(2, getBatchID());
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
						"update division set name = ?, batch_id = ? where division_id = ?;");
				ps.setString(1, getName());
				ps.setInt(2, getBatchID());
				ps.setInt(3, getDivisionID());
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

	public int getDivisionID() {
		return divisionID;
	}

	public void setDivisionID(int divisionID) {
		this.divisionID = divisionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBatchID() {
		return batchID;
	}

	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

}
