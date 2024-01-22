package com.global.hr.data.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.data.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
