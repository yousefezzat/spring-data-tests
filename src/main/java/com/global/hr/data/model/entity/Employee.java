package com.global.hr.data.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employees")
@Entity
@Data
@Builder

public class Employee {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	private Double salary;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
//	@JsonIgnore
	private Department department;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="user_id")
//	@JsonIgnore
	private User user;

	

	public Employee() {
		super();
	}

	public Employee(Long id, String firstName, String lastName, Double salary, Department department, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.department = department;
		this.user = user;
	}



	public Employee(Long id, String firstName, String lastName, Double salary, Department department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

}
