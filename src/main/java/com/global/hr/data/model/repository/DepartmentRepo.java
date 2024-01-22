package com.global.hr.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.data.model.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
		public Department findByName(String name);
}
