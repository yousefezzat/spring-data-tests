package com.global.hr.data.controller;

import java.util.List;

import com.global.hr.data.exception.DepartmentNotFoundException;
import com.global.hr.data.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> getByID(@PathVariable Integer id) {
		try {
			Employee employee = employeeService.getEmp(id);
			return ResponseEntity.ok(employee);
		} catch (EmployeeNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Employee>> filterEmployees(@PathVariable String name) {
		List<Employee> filteredEmployees = employeeService.filter(name);

		if (filteredEmployees.isEmpty()) {
			// If no employees are found, return a 404 Not Found status
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			// If employees are found, return a 200 OK status along with the list of employees
			return new ResponseEntity<>(filteredEmployees, HttpStatus.OK);
		}
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
	public ResponseEntity<?> insert(@RequestBody Employee emp) {
		try {
			Employee insertedEmployee = employeeService.insert(emp);
			return ResponseEntity.ok(insertedEmployee);
		} catch (EmployeeNotFoundException | DepartmentNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}



	@PutMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		return employeeService.update(emp);
	}

	@DeleteMapping("/deleteByID")
	public ResponseEntity<?> deleteEmployee(@RequestParam Integer id) {
		try {
			employeeService.delete(id);
			// If deletion is successful, return a 204 No Content status
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EmployeeNotFoundException e) {
			// If the employee is not found, return a 404 Not Found status with a custom message
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


}
