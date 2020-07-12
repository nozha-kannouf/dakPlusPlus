package View;

import java.sql.SQLException;
import java.util.List;

import model.Employee;
import service.EmployeeService;

public class AccountantView {
	protected void displayEmployees(EmployeeService employeeService) {
		List<Employee> employees = null;

		try {
			employees = employeeService.getAllEmployees();
			employees.forEach(System.out::println);
		} catch (SQLException ignored) {
			System.out.println("Problems with db...");
		}
	}

	protected void createEmployee(EmployeeService employeeService) throws SQLException {
		Employee employee = new Employee();
		System.out.println("Please enter data of the employee: ");
		System.out.println("First name : ");
		employee.setFirstName(Validators.requestStringInput());
		System.out.println("Last name : ");
		employee.setLastName(Validators.requestStringInput());
		System.out.println("Phone : ");
		employee.setPhone(Validators.requestPhoneInput());
		System.out.println("Phone in case of emergency : ");
		employee.setPhoneIce(Validators.requestPhoneInput());
		System.out.println("Date of birth : ");
		employee.setDateOfBirth(Validators.requestAdultInput());
		System.out.println("Salary : ");
		employee.setSalary(Validators.requestSalaryInput());
		employeeService.addEmployee(employee);
		System.out.println("Employee created with success");
	}

	protected void editEmployeById(EmployeeService employeeService) throws SQLException{
		Employee employee = new Employee();
		System.out.println("Please enter employeeId : ");
		System.out.println("id : ");
		employee.setEmployeeId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		System.out.println("First name : ");
		employee.setFirstName(Validators.requestStringInput());
		System.out.println("Last name : ");
		employee.setLastName(Validators.requestStringInput());
		System.out.println("Phone : ");
		employee.setPhone(Validators.requestPhoneInput());
		System.out.println("Phone in case of emergency : ");
		employee.setPhoneIce(Validators.requestPhoneInput());
		System.out.println("Date of birth : ");
		employee.setDateOfBirth(Validators.requestAdultInput());
		System.out.println("Salary : ");
		employee.setSalary(Validators.requestSalaryInput());
		employeeService.updateEmployee(employee);
		System.out.println("Employee edited with success");

	}

	protected void deleteEmployeById(EmployeeService employeeService) throws SQLException{
		Employee employee = new Employee();
		System.out.println("Please enter employeeId : ");
		System.out.println("id : ");
		employee.setEmployeeId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		employeeService.deleteEmployee(employee);
		System.out.println("Employee deleted with success");
	}
	
	
}
