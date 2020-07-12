package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.WorkDone;

public class WorkDoneDAO {

	public Optional<WorkDone> getRecord(WorkDone workDone) {
		Optional<WorkDone> result = null;
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			System.out.println("before select query");
			System.out.println(workDone);
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM workdone  WHERE employeeId = ? AND projectId = ? AND dateOfWork = ?");//
			statement.setInt(1, workDone.getEmployeeId());
			statement.setInt(2, workDone.getProjectId());
			statement.setDate(3, java.sql.Date.valueOf(workDone.getDateOfWork()));
			ResultSet rs = statement.executeQuery();
			System.out.println("after select query");
			List<WorkDone> workdones = parseWorkDone(rs);
			if (workdones.size() == 0)
				result = Optional.empty();
			if (workdones.size() == 1)
				result = Optional.of(workdones.get(0));

		} catch (SQLException e) {
			System.out.println("Problem with the DB!!!!!");
			e.printStackTrace();
		}
		return result;
	}

	public List<WorkDone> getRecords() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM workdone");

		return parseWorkDone(rs);
	}

	public void addRecord(WorkDone workDone) {
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			System.out.println("before insert query");
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO workdone (employeeId, projectId, dateOfWork, hoursWorked, remarks) VALUES ( ?, ?, ?, ?, ?)");
			statement.setInt(1, workDone.getEmployeeId());
			statement.setInt(2, workDone.getProjectId());
			statement.setDate(3, java.sql.Date.valueOf(workDone.getDateOfWork()));
			statement.setInt(4, workDone.getHoursWorked());
			statement.setString(5, workDone.getRemarks());
			if(statement.executeUpdate() == 1) System.out.println("Record added with success");
			else System.out.println("No record added try again ");
			
		} catch (SQLException e) {
			System.out.println("Problem with the DB addRecord method ");
			e.printStackTrace();
		}
	}

	public void updateRecord(WorkDone workDone) {
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"UPDATE workdone SET  dateOfWork= ?, hoursWorked=?, remarks=? WHERE employeeId = ? AND projectId = ?");

			statement.setDate(1, java.sql.Date.valueOf(workDone.getDateOfWork()));
			statement.setInt(2, workDone.getHoursWorked());
			statement.setString(3, workDone.getRemarks());
			statement.setInt(4, workDone.getEmployeeId());
			statement.setInt(5, workDone.getProjectId());
			int recordUpdated = statement.executeUpdate();
			if(recordUpdated == 0) {
				System.out.println("No records updated");
			}else {
				System.out.println("Record(s) updated with success");
			}
		} catch (SQLException e) {
			System.out.println("Problem with the DB of employeeId and/of projectId don't exist in the DB");
			e.printStackTrace();
		}

	}

	public void deleteRecord(WorkDone workDone) {
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("DELETE FROM workdone WHERE employeeId = ? AND projectId = ? AND dateOfWork = ?");
			statement.setInt(1, workDone.getEmployeeId());
			statement.setInt(2, workDone.getProjectId());
			statement.setDate(3, java.sql.Date.valueOf(workDone.getDateOfWork()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<WorkDone> whoWorksWichDay(int employeeId, LocalDate dateOfWork) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<WorkDone> result = null;
		String sql = "select * from (select *   from (select *   from workdone where DAYOFMONTH(dateOfWork)= ?) as s where MONTH(dateOfWork)= ?) as a where YEAR(dateOfWork) = ? AND employeeId = ?";
		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, dateOfWork.getDayOfMonth());
		statement.setInt(2, dateOfWork.getMonthValue());
		statement.setInt(3, dateOfWork.getYear());
		statement.setInt(4, employeeId);
		ResultSet rs = statement.executeQuery();

		result = parseWorkDone(rs);
		if (result.isEmpty()) {
			System.out.println("Employee met id: " + employeeId + " don't Work in the date: " + dateOfWork);
			return result;
		} else
			return result;
	}

	private List<WorkDone> parseWorkDone(ResultSet rs) throws SQLException {
		List<WorkDone> result = new ArrayList<>();
		while (rs.next()) {
			WorkDone workDone = new WorkDone();
			workDone.setEmployeeId(rs.getInt("employeeId"));
			workDone.setProjectId(rs.getInt("projectId"));
			workDone.setDateOfWork(rs.getDate("dateOfWork").toLocalDate());
			workDone.setHoursWorked(rs.getInt("hoursWorked"));
			workDone.setRemarks(rs.getString("remarks"));

			result.add(workDone);

		}

		return result;
	}

}
