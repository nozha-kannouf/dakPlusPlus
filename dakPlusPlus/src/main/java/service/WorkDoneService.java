package service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dao.*;
import model.*;

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
			String concatRemarks = workDone.getRemarks().concat(oldWorkDone.get().getRemarks());
			if(totalHoursWorked<=8) {
			workDone.setHoursWorked(totalHoursWorked);
			workDone.setRemarks(concatRemarks);
			workDoneDAO.updateRecord(workDone);
			}
			else {
				System.out.println("This record can not be added, maximum houres/dag is 8h");
			}
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

	public String profitability(int projectId) {
		double result = 0;
		Project project = new Project();
		
		if (projectDAO.getProject(projectId).isPresent()) {
			project = projectDAO.getProject(projectId).get();
			if(project.getEndDate().isBefore(LocalDate.now())) {
				EmployeeService employeeService = new EmployeeService();
				List<WorkDone> workDones = workDoneDAO.getRecords(projectId);
				result = workDones.stream()
								  .mapToDouble(w-> employeeService.hourlyWage(w.getEmployeeId()) * w.getHoursWorked())
								  .sum();
			}else {
				System.out.println("We can not calculate the profitability because the project is not finished");
				return "";
			}
		}else {
			System.out.println("No such projectId");
		};
		NumberFormat formatter = new DecimalFormat("#0.00");     
		return "The profitability of this project is: "+formatter.format(project.getPrice() - result);
	}

	
	 
	
}
