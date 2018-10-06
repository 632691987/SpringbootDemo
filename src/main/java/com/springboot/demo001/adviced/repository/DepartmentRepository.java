package com.springboot.demo001.adviced.repository;

import com.springboot.demo001.adviced.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {



}
