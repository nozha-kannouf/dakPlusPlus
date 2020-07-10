package service;

import java.sql.SQLException;
import java.util.List;

import dao.ProjectDAO;
import model.Project;

public class ProjectService {
	private ProjectDAO projectDAO = new ProjectDAO();

	public void addProject(Project project) throws SQLException {
		projectDAO.addProject(project);
	}
	
	public void deleteProject(Project project) throws SQLException {
		projectDAO.deleteProject(project);

	}
	public List<Project> getProjectsInProgress() throws SQLException {
		return projectDAO.getProjectsInProgress();
	}

	public List<Project> getProjectsStartingToday() throws SQLException {
		return projectDAO.getProjectsStartingToday();
	}

}
