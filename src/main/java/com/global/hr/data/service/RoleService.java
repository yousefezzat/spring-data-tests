package com.global.hr.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.data.model.entity.Role;
import com.global.hr.data.model.repository.RoleRepo;

@Service
public class RoleService {
	@Autowired
	RoleRepo roleRepo;

	public long roleCount() {
		return roleRepo.count();
	}

	public Role getRole(Long id) {
		Optional<Role> role = roleRepo.findById(id);
		return role.orElse(null);

	}
	public Role getRoleByname(String name) {
		Optional<Role> role = roleRepo.findByName(name);
		return role.orElse(null);
		
	}

	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public Role insert(Role role) {
		
		return roleRepo.save(role);

	}

	public Role update(Role role) {
		return roleRepo.save(role);

	}

	public void delete(Long id) {
		roleRepo.deleteById(id);
	}
	public void clear() {
		roleRepo.deleteAll();;
	}

}
