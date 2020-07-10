package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.WorkDoneDAO;
import model.WorkDone;

public class WorkDoneService {
	private WorkDoneDAO workDoneDAO  = new WorkDoneDAO();
	
	public List<WorkDone> whoWorksWichDay(int employeeId, LocalDate dateOfWork) throws SQLException {
		return workDoneDAO.whoWorksWichDay(employeeId, dateOfWork);
	}

	public List<WorkDone> getRecords() throws SQLException {
		return workDoneDAO.getRecords();
	}
	
	public void addRecord(WorkDone workDone) {
		workDoneDAO.addRecord(workDone);
	}

	public void updateRecord(WorkDone workDone) {
		workDoneDAO.updateRecord(workDone);
	}

	public void deleteRecord(WorkDone workDone)  {
		workDoneDAO.deleteRecord(workDone);

	}
	
}
