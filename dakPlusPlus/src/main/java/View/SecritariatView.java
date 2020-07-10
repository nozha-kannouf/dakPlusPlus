package View;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Employee;
import model.WorkDone;
import service.EmployeeService;
import service.WorkDoneService;

public class SecritariatView {
	//Check if an employee worked on a specific date
	//if you want to check if an employee is working today, just write the date of today
	public void whoWorksWichDay(WorkDoneService workDoneService) {
		List<WorkDone> workDones = null;
		System.out.println("Please enter employeeId : ");
		int employeeId = Validators.requestIntInput(1, Integer.MAX_VALUE);
		System.out.println("Please enter date of work : ");
		LocalDate dateOfWork = Validators.requestDateInput();
		try {
			workDones = workDoneService.whoWorksWichDay(employeeId, dateOfWork);
			workDones.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void findEmployeesByName(EmployeeService employeeService) {
		System.out.println("Please enter the first name of the last name van de employee ");
		List<Employee> employees = null;

		try {
			employees = employeeService.findEmployeesByName(Validators.requestStringInput());
			employees.forEach(System.out::println);
		} catch (SQLException ignored) {
			System.out.println("Problems with db...");
		}

	}
	protected void birthdaysToday(EmployeeService employeeService) throws SQLException {
		List<Employee> result = employeeService.getEmployesBornToday();
		if (result.isEmpty()) {
			System.out.println("No employee celebrates his birthday today");
		} else
			System.out.println("Today don't forget to wish happy birthday to :");
			result.forEach(System.out :: println);

	}
	protected void birthdaysThisWeek(EmployeeService employeeService) throws SQLException {
		List<Employee> result = employeeService.getEmployesBornThisWeek();
		if (result.isEmpty()) {
			System.out.println("No employee celebrates his birthday in this week");
		} else
			System.out.println("This week don't forget to wish happy birthday to :");
			result.forEach(System.out :: println);

	}

	

}
