package com.simplilearn.webapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ems_project")
public class Project {

	// properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int id;

	@Column(name = "project_no")
	private String projectNo;

	@Column(name = "project_title")
	private String projectTitle;

	// many to many
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "projects__employees", joinColumns = @JoinColumn(name = "project_id"), 
	inverseJoinColumns = @JoinColumn(name = "emp_id"))
	private Set<Employee> employees;

	// constructor
	public Project(String projectNo, String projectTitle) {
		this.projectNo = projectNo;
		this.projectTitle = projectTitle;
	}

	public Project() {
		super();
	}

	// getter & setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	// tostring
	@Override
	public String toString() {
		return "Project [id=" + id + ", projectNo=" + projectNo + ", projectTitle=" + projectTitle + "]";
	}
}
