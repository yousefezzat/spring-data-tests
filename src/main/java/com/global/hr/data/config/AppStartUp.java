package com.global.hr.data.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.hr.data.model.entity.Role;
import com.global.hr.data.model.entity.User;
import com.global.hr.data.service.RoleService;
import com.global.hr.data.service.UserService;

//@Component
public class AppStartUp implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		if (userService.findAll().isEmpty() || roleService.findAll().isEmpty()) 
		{
			Role role1 = new Role();
			role1.setName("Admin");
			roleService.insert(role1);

			Role role2 = new Role();
			role2.setName("Moderator");
			roleService.insert(role2);

			Role role3 = new Role();
			role3.setName("User");
			roleService.insert(role3);

			Role role4 = new Role();
			role4.setName("Userr");
			roleService.insert(role4);

			Set<Role> Admins = new HashSet<>();
			Admins.add(role1);
			Admins.add(role2);

			Set<Role> Users = new HashSet<>();
			Users.add(role3);
			Users.add(role4);

			User user1 = new User();
			user1.setUsername("yousef");
			user1.setPassword("512");
			user1.setRole(Admins);
			userService.insert(user1);

			User user2 = new User();
			user2.setUsername("Ramy");
			user2.setPassword("1024");
			user2.setRole(Users);
			userService.insert(user2);
		}

	}

}
