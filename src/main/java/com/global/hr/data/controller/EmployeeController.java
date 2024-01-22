package com.global.hr.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.service.EmployeeService;

@RestController
@RequestMapping("/empData")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/count")
	public long empCount() {
		return employeeService.empCount();
	}

	@GetMapping("/findAll")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/get/{id}")
	public Employee getByID(@PathVariable Integer id) {
		return employeeService.getEmp(id);
	}

	@GetMapping("/getByName/{name}")
	public List<Employee> getByName(@PathVariable String name) {
		return employeeService.filter(name);
	}

	@GetMapping("/getByNameLike/{name}")
	public List<Employee> getLike(@PathVariable String name) {
		return employeeService.filterSimilar(name);
	}
	@GetMapping("/getByDepartmentId/{id}")
	public List<Employee> getByDeptId(@PathVariable Long id) {
		return employeeService.findByDeptId(id);
	}
	@GetMapping("/getByDepartmentIdJPQL/{id}")
	public List<Employee> getByDeptJPQL(@PathVariable Long id) {
		return employeeService.findByDeptJPQL(id);
	}

	@PostMapping("/insert")
	public ResponseEntity<Employee> insert(@RequestBody Employee emp) {
		return employeeService.insert(emp);
	}

	@PutMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		return employeeService.update(emp);
	}

	@DeleteMapping("/deleteByID")
	public void deleteById(@RequestParam Integer id) {
		employeeService.delete(id);
	}

}
