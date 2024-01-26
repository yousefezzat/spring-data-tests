package com.global.hr.data.controller;

import com.global.hr.data.exception.DepartmentNotFoundException;
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

import com.global.hr.data.model.entity.Department;
import com.global.hr.data.service.DepartmentService;

@RestController
@RequestMapping("/depData")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	@GetMapping("/count")
	public long DepCount() {
		return departmentService.depCount();
	}

	@GetMapping("/findAll")
	public Iterable<Department> findAll() {
		return departmentService.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getByID(@PathVariable Long id) {
		try {
			Department department = departmentService.getDep(id);
			return ResponseEntity.ok(department);
		} catch (DepartmentNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PostMapping("/insert")
	public Department insert(@RequestBody Department Dep) {
		return departmentService.insert(Dep);
	}

	@PutMapping("/update")
	public Department update(@RequestBody Department Dep) {
		return departmentService.update(Dep);
	}

	@DeleteMapping("/deleteByID")
	public void deleteById(@RequestParam Long id) {
		departmentService.delete(id);
	}
	@DeleteMapping("/deleteAll")
	public void deleteById() {
		departmentService.clear();
	}

}
