package com.global.hr.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Department getByID(@PathVariable Long id) {
		return departmentService.getDep(id);
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
