package com.springboot.demo001.adviced.cache.repository;

import com.springboot.demo001.adviced.cache.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {



}
