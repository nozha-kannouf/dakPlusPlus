package model;

import java.time.LocalDate;


public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String phone;
	private String phoneIce;
	private LocalDate dateOfBirth;
	private Double salary;

	public int getEmployeeId() {
		return employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhoneIce() {
		return phoneIce;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Double getSalary() {
		return salary;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhoneIce(String phoneIce) {
		this.phoneIce = phoneIce;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee ["
				+ "employeeId=" + employeeId 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", phone=" + phone 
				+ ", phoneIce=" + phoneIce 
				+ ", dateOfBirth=" + dateOfBirth 
				+ ", salary=" + salary 
				+ "]";
	}

	
}
