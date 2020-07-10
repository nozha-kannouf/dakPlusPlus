package service;

import java.sql.SQLException;
import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();

	public List<Employee> getAllEmployees() throws SQLException {
		return employeeDAO.getAllEmployees();
	}

	public List<Employee> findEmployeesByName(String name) throws SQLException {
		return employeeDAO.findEmployeesByName(name);
	}

	public void addEmployee(Employee employee) throws SQLException {
		employeeDAO.addEmployee(employee);
	}

	public void updateEmployee(Employee employee) throws SQLException {
		employeeDAO.updateEmployee(employee);
	}

	public void deleteEmployee(Employee employee) throws SQLException {
		employeeDAO.deleteEmployee(employee);

	}

	public List<Employee> getEmployesBornToday() throws SQLException {
		return employeeDAO.getEmployesBornToday();

	}

	public List<Employee> getEmployesBornThisWeek() throws SQLException {
		return employeeDAO.getEmployesBornThisWeek();
	}

}
