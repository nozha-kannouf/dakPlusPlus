package View;

import java.sql.SQLException;

import service.EmployeeService;
import service.WorkDoneService;
import service.ProjectService;;

public class Main {

	public static void main(String[] args) throws SQLException {
		int mainChoice;
		int subChoice = -1;

		do {
			showMenu();
			mainChoice = Validators.requestIntInput(0, 4);
			if (mainChoice != 0) {
				showSubMenu(mainChoice);
				subChoice = Validators.requestIntInput(0, 6);

				handleUserChoice(mainChoice, subChoice);
			}
		} while (mainChoice != 0 && subChoice != 0);
	}

	private static void handleUserChoice(int mainChoice, int subChoice) throws SQLException {
		EmployeeService employeeService = new EmployeeService();
		WorkDoneService workDoneService = new WorkDoneService();
		ProjectService  projectService = new ProjectService();
		if (mainChoice == 1) {
			AccountantView accountantView = new AccountantView();

			if (subChoice == 1) {
				accountantView.displayEmployees(employeeService);
			}

			if (subChoice == 2) {
				accountantView.createEmployee(employeeService);
			}
			if (subChoice == 3) {
				accountantView.editEmployeById(employeeService);
			}
			if (subChoice == 4) {
				accountantView.deleteEmployeById(employeeService);
			}
		}
		if (mainChoice == 2) {
			SecritariatView secritariatView = new SecritariatView();

			if (subChoice == 1) {
				secritariatView.whoWorksWichDay(workDoneService);
			}

			if (subChoice == 2) {
				secritariatView.findEmployeesByName(employeeService);
			}

			if (subChoice == 3) {
				secritariatView.birthdaysToday(employeeService);
			}
			if (subChoice == 4) {
				secritariatView.birthdaysThisWeek(employeeService);
			}

		}
		if (mainChoice == 3) {
			ProjectManagementView projectManagementView = new ProjectManagementView();

			if (subChoice == 1) {
				projectManagementView.addProject(projectService);
			}

			if (subChoice == 2) {
				projectManagementView.deleteProjectById(projectService);
			}

			if (subChoice == 3) {
				projectManagementView.getProjectsInProgress(projectService);
			}
			if (subChoice == 4) {
				projectManagementView.getProjectsStartingToday(projectService);
			}

		}
		if (mainChoice == 4) {
			ManagementView managementView = new ManagementView();

			if (subChoice == 1) {
				managementView.displayRecords(workDoneService);
			}

			if (subChoice == 2) {
				managementView.addRecord(workDoneService);
			}

			if (subChoice == 3) {
				managementView.updateRecord(workDoneService);
			}
			if (subChoice == 4) {
				managementView.deleteRecord(workDoneService);
			}

		}

	}

	private static void showMenu() {
		System.out.println("\n Chose the view to display: ");
		System.out.println("0. Exit");
		System.out.println("1. Accountant View");
		System.out.println("2. Secretariat View");
		System.out.println("3. Project Management View");
		System.out.println("4. Management View");
	}

	private static void showSubMenu(int choice) {
		if (choice == 1) {
			System.out.println("0. Exit");
			System.out.println("1. Show all employees");
			System.out.println("2. Create a new employee");
			System.out.println("3. Edit an employee by id");
			System.out.println("4. Delete an employee by id");
		}

		if (choice == 2) {
			System.out.println("0. Exit");
			System.out.println("1. Checking if an employee is working by id and date of Work");
			System.out.println("2. Find an employee(s) by name");
			System.out.println("3. who is celebrating his birthday today?");
			System.out.println("4. who is celebrating his birthday this week?");
		}
		if (choice == 3) {
			System.out.println("0. Exit");
			System.out.println("1. Create a new project");
			System.out.println("2. Delete a project");
			System.out.println("3. List of projects in progress");
			System.out.println("4. list of projects starting today");
		}
		if (choice == 4) {
			System.out.println("0. Exit");
			System.out.println("1. Display workDone records");
			System.out.println("2. Create workDone record");
			System.out.println("3. Update workDone record");
			System.out.println("4. Delete workDone record");
		}
	}

}
