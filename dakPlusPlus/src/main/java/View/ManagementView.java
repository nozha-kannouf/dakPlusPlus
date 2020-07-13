package View;

import java.sql.SQLException;
import java.util.List;

import model.WorkDone;
import service.WorkDoneService;;

public class ManagementView {

	protected void displayRecords(WorkDoneService workDoneService) {
		List<WorkDone> workDones = null;
		try {
			workDones = workDoneService.getRecords();
			workDones.forEach(System.out::println);
		} catch (SQLException ignored) {
			System.out.println("Problems with db...");
		}
	}

	protected void addRecord(WorkDoneService workDoneService) {
		WorkDone workDone = new WorkDone();
		System.out.println("employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("projectId : ");
		workDone.setProjectId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("Date of work : ");
		workDone.setDateOfWork(Validators.requestDateInput());
		System.out.println("Number of hours worked ");
		workDone.setHoursWorked(Validators.requestIntInput(0, 8));
		System.out.println("Remarks : ");
		workDone.setRemarks(Validators.requestOptionalStringInput());
		workDoneService.addRecord(workDone);
	}

	protected void updateRecord(WorkDoneService workDoneService) {
		WorkDone workDone = new WorkDone();
		System.out.println("employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("projectId : ");
		workDone.setProjectId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("Date of work : ");
		workDone.setDateOfWork(Validators.requestDateInput());
		System.out.println("Number of hours worked ");
		workDone.setHoursWorked(Validators.requestIntInput(0, 8));
		System.out.println("Remarks : ");
		workDone.setRemarks(Validators.requestOptionalStringInput());
		workDoneService.updateRecord(workDone);

	}

	protected void deleteRecord(WorkDoneService workDoneService) {
		WorkDone workDone = new WorkDone();
		System.out.println("Please enter employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		System.out.println("Please enter projectId : ");
		workDone.setProjectId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		System.out.println("Please enter date of work : ");
		workDone.setDateOfWork(Validators.requestDateInput());
		workDoneService.deleteRecord(workDone);
	}

	protected void profitability(WorkDoneService workDoneService) {
		System.out.println("Please enter projectId : ");
		String result = workDoneService.profitability(Validators.requestIntInput(1, Integer.MAX_VALUE));
		System.out.println(result);
	}

}
