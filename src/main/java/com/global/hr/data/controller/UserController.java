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

import com.global.hr.data.model.entity.User;
import com.global.hr.data.service.UserService;

@RestController
@RequestMapping("/userData")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/count")
	public long UserCount() {
		return userService.userCount();
	}

	@GetMapping("/findAll")
	public Iterable<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/get/{id}")
	public User getByID(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@PostMapping("/insert")
	public User insert(@RequestBody User User) {
		return userService.insert(User);
	}

	@PutMapping("/update")
	public User update(@RequestBody User User) {
		return userService.update(User);
	}

	@DeleteMapping("/deleteByID")
	public void deleteById(@RequestParam Long id) {
		userService.delete(id);
	}
	@DeleteMapping("/deleteAll")
	public void deleteById() {
		userService.clear();
	}

}
