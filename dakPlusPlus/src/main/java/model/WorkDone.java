package model;

import java.time.LocalDate;

public class WorkDone {
	private int employeeId;
	private int projectId;
	private LocalDate dateOfWork;
	private int hoursWorked;
	private String remarks;

	public int getEmployeeId() {
		return employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public LocalDate getDateOfWork() {
		return dateOfWork;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setDateOfWork(LocalDate dateOfWork) {
		this.dateOfWork = dateOfWork;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "WorkDone ["
				+ "employeeId=" + employeeId 
				+ ", projectId=" + projectId 
				+ ", dateOfWork=" + dateOfWork
				+ ", hoursWorked=" + hoursWorked 
				+ ", remarks=" + remarks 
				+ "]";
	}

	
}
