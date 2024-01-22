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

import com.global.hr.data.model.entity.Role;
import com.global.hr.data.service.RoleService;

@RestController
@RequestMapping("/roleData")
public class RoleController {
	@Autowired
	RoleService roleService;

	@GetMapping("/count")
	public long RoleCount() {
		return roleService.roleCount();
	}

	@GetMapping("/findAll")
	public Iterable<Role> findAll() {
		return roleService.findAll();
	}

	@GetMapping("/get/{id}")
	public Role getByID(@PathVariable Long id) {
		return roleService.getRole(id);
	}
	@GetMapping("/getByName/{name}")
	public Role getByID(@PathVariable String name) {
		return roleService.getRoleByname(name);
	}

	@PostMapping("/insert")
	public Role insert(@RequestBody Role Role) {
		return roleService.insert(Role);
	}

	@PutMapping("/update")
	public Role update(@RequestBody Role Role) {
		return roleService.update(Role);
	}

	@DeleteMapping("/deleteByID")
	public void deleteById(@RequestParam Long id) {
		roleService.delete(id);
	}
	@DeleteMapping("/deleteAll")
	public void deleteById() {
		roleService.clear();
	}

}
