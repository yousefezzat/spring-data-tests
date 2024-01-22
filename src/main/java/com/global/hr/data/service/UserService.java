package com.global.hr.data.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.data.model.entity.Role;
import com.global.hr.data.model.entity.User;
import com.global.hr.data.model.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleService roleService;

	public long userCount() {
		return userRepo.count();
	}

	public User getUser(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.orElse(null);

	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User insert(User user) {  //when you insert new user you should insert its roles too
		Set<Role> verified = new HashSet<>();
		
		for (Role r : user.getRole()) {
			if (r != null && r.getId() != null) { //so here if the role already exist (by id)add it to the user 
				
				verified.add(roleService.getRole(r.getId()));
			}
			else if(roleService.getRoleByname(r.getName())!=null) { //and here if it exists (by name) "I made the role name unique too
				verified.add(roleService.getRoleByname(r.getName()));
			}
			else { //finally if the role new then insert it in roles table 
				Role newRole = roleService.insert(r);
				verified.add(newRole);
			}
		}
		user.setRole(verified);
		
		return userRepo.save(user);

	}

	public User update(User user) {
		return userRepo.save(user);

	}

	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	public void clear() {
		userRepo.deleteAll();
		;
	}

}
