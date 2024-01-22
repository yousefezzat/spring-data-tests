package com.global.hr.data.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.global.hr.data.model.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
		public Optional<Role> findByName(String name);
}
