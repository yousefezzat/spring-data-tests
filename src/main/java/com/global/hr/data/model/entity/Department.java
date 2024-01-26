package com.global.hr.data.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	Long id;
	@Column(name = "department_name", unique = true)
	String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> employees;


////   @OneToMany(mappedBy = "department")  //bidirectional
//	List<Employee> employees;

	public Department(Long id, String name) {

		this.id = id;
		this.name = name;
	}

	public Department() {

	}

	public Department(String name) {

		this.name = name;

	}


//	public Department(Long id, String name, List<Employee> employees) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.employees = employees;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}

}
