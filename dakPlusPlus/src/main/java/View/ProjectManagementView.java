package View;


import service.ProjectService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Project;

public class ProjectManagementView {

	protected void addProject(ProjectService projectService) {
		Project project = new Project();
		System.out.println("Please add the data of the project: ");
		System.out.println("Name : ");
		project.setName(Validators.requestStringInput());
		System.out.println("Start date: ");
		LocalDate startDate = Validators.requestStartDateNotInThePastInput();
		project.setStartDate(startDate);
		System.out.println("End date: ");
		project.setEndDate(Validators.requestEndDateNotInThePastInput(startDate));
		System.out.println("Description : ");
		project.setDescription(Validators.requestStringInput());
		System.out.println("Price : ");
		project.setPrice(Validators.requestPriceInput());

		try {
			projectService.addProject(project);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void deleteProjectById(ProjectService projectService) throws SQLException{
		Project project = new Project();
		System.out.println("Please enter projectId : ");
		project.setProjectId(Validators.requestIntInput(1, Integer.MAX_VALUE));
		projectService.deleteProject(project);
		System.out.println("Project deleted with success");
	}
	protected void getProjectsInProgress(ProjectService projectService) {
		try {
			List<Project> projects = projectService.getProjectsInProgress();
			if(projects.isEmpty()) {
				System.out.println("No projects in progress! ");
			}else {
			System.out.println("Projects in progress are: ");
			projects.forEach(System.out :: println);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void getProjectsStartingToday(ProjectService projectService) {
		try {
			List<Project> projects = projectService.getProjectsStartingToday();
			if(projects.isEmpty()) {
				System.out.println("No projects starting today! ");
			}else {
			System.out.println("Projects starting today are: ");
			projects.forEach(System.out :: println);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
