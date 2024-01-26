package com.global.hr.data.service;

import java.util.List;
import java.util.Optional;

import com.global.hr.data.exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.data.model.entity.Department;
import com.global.hr.data.model.repository.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepo departmentRepo;

	public long depCount() {
		return departmentRepo.count();
	}

	public Department getDep(Long id) {
		Optional<Department> dep = departmentRepo.findById(id);
		return dep.orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
	}
	public Department getDepartmentByname(String name) {
		return departmentRepo.findByName(name) ;
		
	}

	
	public List<Department> findAll() {
		return departmentRepo.findAll();
	}

	public Department insert(Department dep) {
		
		return departmentRepo.save(dep);

	}

	public Department update(Department dep) {
		return departmentRepo.save(dep);

	}

	public void delete(Long id) {
		departmentRepo.deleteById(id);
	}
	public void clear() {
		departmentRepo.deleteAll();;
	}

}
