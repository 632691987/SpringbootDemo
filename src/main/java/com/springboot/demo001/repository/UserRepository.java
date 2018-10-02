package com.springboot.demo001.repository;

import com.springboot.demo001.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
