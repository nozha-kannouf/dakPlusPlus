package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Employee;
import model.Project;

public class ProjectDAO {

	public void addProject(Project project) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(
				"INSERT INTO project(name, startDate, endDate, description, price ) VALUES ( ?, ?, ?, ?, ?)");
		statement.setString(1, project.getName());
		statement.setDate(2, java.sql.Date.valueOf(project.getStartDate()));
		statement.setDate(3, java.sql.Date.valueOf(project.getEndDate()));
		statement.setString(4, project.getDescription());
		statement.setDouble(5, project.getPrice());
		statement.executeUpdate();
		System.out.println("A Project is created with success ");

	}

	public void deleteProject(Project project) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement("DELETE FROM project WHERE projectId = ?");
		statement.setInt(1, project.getProjectId());
		statement.executeUpdate();

	}

	public List<Project> getProjectsInProgress() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<Project> result = null;
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM project where endDate > ?");
		statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
		ResultSet rs = statement.executeQuery();

		return result = parseProjects(rs);
	}
	
	public List<Project> getProjectsStartingToday() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		List<Project> result = null;
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM project where startDate = ?");
		statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
		ResultSet rs = statement.executeQuery();

		return result = parseProjects(rs);
	}
	public Optional<Project> getProject(int projectId) {
		Optional<Project> result = null;
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM project WHERE projectId = ? ");//
			statement.setInt(1, projectId);
			ResultSet rs = statement.executeQuery();
			List<Project> projects = parseProjects(rs);
			if (projects.size() == 0)
				result = Optional.empty();
			if (projects.size() == 1)
				result = Optional.of(projects.get(0));

		} catch (SQLException e) {
			System.out.println("Problem with the DB!!!!!");
			e.printStackTrace();
		}
		return result;
	}
	public boolean projectIdExists(int id){
		boolean exist = false;
		if(getProject(id)== null) {
			System.out.println("No such projectId");
			exist = false;
		}else {
			exist = true;
		}
		return exist;
	}
	private List<Project> parseProjects(ResultSet rs) throws SQLException {
		List<Project> result = new ArrayList<>();
		while (rs.next()) {
			Project project = new Project();
			project.setProjectId(rs.getInt("projectId"));
			project.setName(rs.getString("name"));
			project.setStartDate(rs.getDate("startDate").toLocalDate());
			project.setEndDate(rs.getDate("endDate").toLocalDate());
			project.setDescription(rs.getString("description"));
			project.setPrice(rs.getDouble("price"));

			result.add(project);
		}
		return result;
	}
}
