package com.global.hr.data.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.data.model.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>  {
	public List<Employee> findByFirstName(String name);
	public List<Employee> findByDepartmentId(Long id);

	// JPQL (to make out code independent on database)
	@Query(value = "select emp from Employee emp where emp.firstName like :empName")
	List<Employee> filterSimilar(@Param("empName") String name);
	
	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :id")
	public List<Employee> findByDepartmentJPQL(Long id);

	// we can write sql native to (@query)
	
	

}
