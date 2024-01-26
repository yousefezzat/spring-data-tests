package com.global.hr.data.service;

import java.util.List;
import java.util.Optional;

import com.global.hr.data.exception.DepartmentNotFoundException;
import com.global.hr.data.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.global.hr.data.model.entity.Department;
import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.model.repository.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    DepartmentService departmentService;


    public long empCount() {
        return employeeRepo.count();
    }

    public Employee getEmp(Integer id) {

        return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }


//		or
//		Optional<Employee> emp =employeeRepo.findById(id);
//		return emp.orElse(null);


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

    public Employee insert(Employee emp) {
        if (emp.getDepartment() != null) {
            if (emp.getDepartment().getId() != null) {
                // Check if the department with the provided ID exists
                Department existingDepartment = departmentService.getDep(emp.getDepartment().getId());
                emp.setDepartment(existingDepartment);
            } else if (emp.getDepartment().getName() != null) {
                // Check if a department with the provided name exists
                Department existingDepartment = departmentService.getDepartmentByname(emp.getDepartment().getName());

                if (existingDepartment != null) {
                    // If it exists, use the existing department
                    emp.setDepartment(existingDepartment);
                } else {
                    // If it doesn't exist, throw a DepartmentNotFoundException
                    throw new DepartmentNotFoundException("Department with name " + emp.getDepartment().getName() + " not found");
                }
            } else {
                // If neither ID nor name is provided, throw an exception
                throw new DepartmentNotFoundException("Department ID or name must be provided");
            }
        }

        return employeeRepo.save(emp);
    }


    public Employee update(Employee emp) {
        return employeeRepo.save(emp);

    }

    public void delete(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepo.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }


}
