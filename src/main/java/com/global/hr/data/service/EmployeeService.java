package com.global.hr.data.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.global.hr.data.model.entity.Department;
import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.model.entity.Role;
import com.global.hr.data.model.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	public long empCount() {
		return employeeRepo.count();
	}

	public Employee getEmp(Integer id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if (emp.isPresent())
			return emp.get();
		return null;

//		or 
//		Optional<Employee> emp =employeeRepo.findById(id);
//		return emp.orElse(null);

	}

	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}

	public List<Employee> findByDeptId(Long id) {
		return employeeRepo.findByDepartmentId(id);

	}

	public List<Employee> findByDeptJPQL(Long id) {
		return employeeRepo.findByDepartmentJPQL(id);

	}

	public List<Employee> filter(String name) {
		return employeeRepo.findByFirstName(name);

	}

	public List<Employee> filterSimilar(String name) {
		return employeeRepo.filterSimilar(name);

	}

//	public ResponseEntity<Employee> insert(Employee emp) {
//		if(emp.getDepartment()!=null && emp.getDepartment().getId()!=null) {
//			emp.setDepartment(departmentService.getDep(emp.getDepartment().getId()));
//		}
////		else if (emp.getDepartment() != null&& emp.getDepartment().getName() !=null && emp.getDepartment().getName().equalsIgnoreCase(departmentService.getDepartmentByname(emp.getDepartment().getName()).getName())) {
////			emp.setDepartment(departmentService.getDepartmentByname(emp.getDepartment().getName()));
////		}
//		
//		if(emp.getUser()!=null && emp.getUser().getId()!=null) {
//			emp.setUser(userService.getUser(emp.getUser().getId()));
//		}
//		
//		
//		return ResponseEntity.ok(employeeRepo.save(emp));
//	}
	public ResponseEntity<Employee> insert(Employee emp) {
		if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			emp.setDepartment(departmentService.getDep(emp.getDepartment().getId()));
		}
		if (emp.getDepartment() != null && emp.getDepartment().getName() != null) {
			// Check if a department with the same name already exists
			Department existingDepartment = departmentService.getDepartmentByname(emp.getDepartment().getName());

			if (existingDepartment != null) {
				// If it exists, use the existing department
				emp.setDepartment(existingDepartment);
			} else {
				// If it doesn't exist, create and save a new department
				Department newDepartment = new Department(emp.getDepartment().getName());
				departmentService.insert(newDepartment);
				emp.setDepartment(newDepartment);
			}
		}

		if (emp.getUser() != null && emp.getUser().getId() != null) {
			emp.setUser(userService.getUser(emp.getUser().getId()));
		} else if (emp.getUser() != null) {
			// Ensure that all associated Role entities are managed by the session.
			Set<Role> verifiedRoles = new HashSet<>();
			for (Role role : emp.getUser().getRole()) {
				if (role.getId() != null) {
					verifiedRoles.add(roleService.getRole(role.getId()));
				} else if (role.getName() != null) {
					Role existingRole = roleService.getRoleByname(role.getName());
					if (existingRole != null) {
						verifiedRoles.add(existingRole);
					} else {
						// Save new Role and add it to the set.
						Role newRole = roleService.insert(role);
						verifiedRoles.add(newRole);
					}
				}
			}
			emp.getUser().setRole(verifiedRoles);
		}

		return ResponseEntity.ok(employeeRepo.save(emp));
	}

	public Employee insert2(Employee emp) { // to use it in the console
		return employeeRepo.save(emp);

	}

	public Employee update(Employee emp) {
		return employeeRepo.save(emp);

	}

	public void delete(Integer id) {
		employeeRepo.deleteById(id);
	}

}
