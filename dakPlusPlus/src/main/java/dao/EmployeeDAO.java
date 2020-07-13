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

import model.Employee;


public class EmployeeDAO {
	public Optional<Employee> getEmployee(int employeeId) {
		Optional<Employee> result = null;
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM employee WHERE employeeId = ? ");//
			statement.setInt(1, employeeId);
			ResultSet rs = statement.executeQuery();
			List<Employee> employees = parseEmployees(rs);
			if (employees.size() == 0)
				result = Optional.empty();
			if (employees.size() == 1)
				result = Optional.of(employees.get(0));

		} catch (SQLException e) {
			System.out.println("Problem with the DB!!!!!");
			e.printStackTrace();
		}
		return result;
	}
	public boolean employeeIdExists(int id){
		boolean exist = false;
		if(getEmployee(id)== null) {
			System.out.println("No such employeeId");
			exist = false;
		}else {
			exist = true;
		}
		
//		Connection conn;
//		try {
//			conn = ConnectionFactory.getConnection();
//			List<Employee> result = null;
//			PreparedStatement statement = conn
//					.prepareStatement("SELECT * FROM employee WHERE employeeId = ?");
//			statement.setInt(1, id);
//			ResultSet rs = statement.executeQuery();
//
//			result = parseEmployees(rs);
//			if (result.isEmpty()) {
//				System.out.println("No such employeeId");
//				exist = false;
//			} else
//				exist = true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return exist;
	}
	public List<Employee> getAllEmployees() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM employee");

		return parseEmployees(rs);
	}

	public void addEmployee(Employee employee) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(
				"INSERT INTO employee(firstName, lastName, phone, phoneICE, dateOfBirth, salary ) VALUES ( ?, ?, ?, ?, ?, ?)");
		statement.setString(1, employee.getFirstName());
		statement.setString(2, employee.getLastName());
		statement.setString(3, employee.getPhone());
		statement.setString(4, employee.getPhoneIce());
		statement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBirth()));
		statement.setDouble(6, employee.getSalary());
		statement.executeUpdate();

	}

	public void updateEmployee(Employee employee) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(
				"UPDATE employee SET firstName=?, lastName= ?, phone= ?, phoneICE=?, dateOfBirth=?, salary=? WHERE employeeId = ?");
		statement.setString(1, employee.getFirstName());
		statement.setString(2, employee.getLastName());
		statement.setString(3, employee.getPhone());
		statement.setString(4, employee.getPhoneIce());
		statement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBirth()));
		statement.setDouble(6, employee.getSalary());
		statement.setInt(7, employee.getEmployeeId());

		statement.executeUpdate();

	}

	public void deleteEmployee(Employee employee) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement("DELETE FROM employee WHERE employeeId = ?");
		statement.setInt(1, employee.getEmployeeId());
		statement.executeUpdate();

	}

	public List<Employee> findEmployeesByName(String name) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<Employee> result = null;
		PreparedStatement statement = conn
				.prepareStatement("SELECT * FROM employee WHERE firstName Like ? OR lastName LIKE ?");
		statement.setString(1, name);
		statement.setString(2, name);
		ResultSet rs = statement.executeQuery();

		result = parseEmployees(rs);
		if (result.isEmpty()) {
			System.out.println("No employee with the name : " + name);
			return result;
		} else
			return result;
	}

	private List<Employee> parseEmployees(ResultSet rs) throws SQLException {
		List<Employee> result = new ArrayList<>();
		while (rs.next()) {
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("employeeId"));
			employee.setFirstName(rs.getString("firstName"));
			employee.setLastName(rs.getString("lastName"));
			employee.setPhone(rs.getString("phone"));
			employee.setPhoneIce(rs.getString("phoneICE"));
			employee.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
			employee.setSalary(rs.getDouble("salary"));

			result.add(employee);

		}

		return result;
	}

	public List<Employee> getEmployesBornToday() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<Employee> result = null;
		PreparedStatement statement = conn.prepareStatement(
				"select *   from (select *   from employee where DAYOFMONTH(dateOfBirth)= ?) as s where MONTH(dateOfBirth)= ? ");
		statement.setInt(1, LocalDate.now().getDayOfMonth());
		statement.setInt(2, LocalDate.now().getMonthValue());
		ResultSet rs = statement.executeQuery();
		return result = parseEmployees(rs);
	}
	public List<Employee> getEmployesBornThisWeek() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<Employee> result = null;
		PreparedStatement statement = conn.prepareStatement(
				"select *   from (select *   from employee where DAYOFMONTH(dateOfBirth) BETWEEN ? AND ?) as s where MONTH(dateOfBirth)= ? ");
		statement.setInt(1, LocalDate.now().getDayOfMonth());
		statement.setInt(2, LocalDate.now().getDayOfMonth()+7);
		statement.setInt(3, LocalDate.now().getMonthValue());
		ResultSet rs = statement.executeQuery();
		return result = parseEmployees(rs);
	}

}
