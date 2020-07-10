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
		//Ik kan beter doen met deze function, als projectId, employeeId and dateOfWorks zijn al in de DB
		//add nieuw hours to old hours
		//concat old remarks met nieuw remarks
		WorkDone workDone  = new WorkDone();
		System.out.println("employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("employeeId : ");
		workDone.setProjectId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("Date of work : ");
		workDone.setDateOfWork(Validators.requestDateInput());
		System.out.println("Number of hours worked ");
		workDone.setHoursWorked(Validators.requestIntInput(0, 10));
		System.out.println("Remarks : ");
		workDone.setRemarks(Validators.requestOptionalStringInput());
		workDoneService.addRecord(workDone);
		System.out.println("Record created with success");
	}

	protected void updateRecord(WorkDoneService workDoneService){
		WorkDone workDone  = new WorkDone();
		System.out.println("employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("employeeId : ");
		workDone.setProjectId(Validators.requestIntInput(0, Integer.MAX_VALUE));
		System.out.println("Date of work : ");
		workDone.setDateOfWork(Validators.requestDateInput());
		System.out.println("Number of hours worked ");
		workDone.setHoursWorked(Validators.requestIntInput(0, 10));
		System.out.println("Remarks : ");
		workDone.setRemarks(Validators.requestOptionalStringInput());
		workDoneService.addRecord(workDone);
		System.out.println("Record created with success");
	}

	protected void deleteRecord(WorkDoneService workDoneService){
		WorkDone workDone = new WorkDone();
		System.out.println("Please enter employeeId : ");
		workDone.setEmployeeId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		workDone.setProjectId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		workDoneService.deleteRecord(workDone);
		System.out.println("Record deleted with success");
	}
	
}
