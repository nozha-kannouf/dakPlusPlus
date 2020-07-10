package model;

import java.time.LocalDate;
import java.util.UUID;

public class Project {
	private int projectId;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private double price;

	public int getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Project ["
				+ "projectId=" + projectId 
				+ ", name=" + name 
				+ ", startDate=" + startDate 
				+ ", endDate="+ endDate 
				+ ", description=" + description 
				+ ", price=" + price 
				+ "]";
	}

	
}
