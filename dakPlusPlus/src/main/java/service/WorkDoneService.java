package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dao.EmployeeDAO;
import dao.ProjectDAO;
import dao.WorkDoneDAO;
import model.WorkDone;

public class WorkDoneService {
	private WorkDoneDAO workDoneDAO  = new WorkDoneDAO();
	private ProjectDAO projectDAO = new ProjectDAO();
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public List<WorkDone> whoWorksWichDay(int employeeId, LocalDate dateOfWork) throws SQLException {
		return workDoneDAO.whoWorksWichDay(employeeId, dateOfWork);
	}

	public List<WorkDone> getRecords() throws SQLException {
		return workDoneDAO.getRecords();
	}
	
	public void addRecord(WorkDone workDone) {
		Optional<WorkDone> oldWorkDone = workDoneDAO.getRecord(workDone);
		if(oldWorkDone.isEmpty()) {
		workDoneDAO.addRecord(workDone);
		}else {
			int totalHoursWorked = workDone.getHoursWorked()+ oldWorkDone.get().getHoursWorked();
			String concatRemarks = workDone.getRemarks().concat(oldWorkDone.get().getRemarks());workDone.setHoursWorked(totalHoursWorked);
			workDone.setRemarks(concatRemarks);
			workDoneDAO.updateRecord(workDone);
		}
	}

	public void updateRecord(WorkDone workDone) {
		boolean employeeId = employeeDAO.employeeIdExists(workDone.getEmployeeId());
		boolean projectId = projectDAO.projectIdExists(workDone.getProjectId());
		if(employeeId & projectId) {
		workDoneDAO.updateRecord(workDone);
		}
	}

	public void deleteRecord(WorkDone workDone)  {
		workDoneDAO.deleteRecord(workDone);

	}
	
}
