package com.springboot.demo001.adviced.repository;

import com.springboot.demo001.adviced.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findById(Integer id);

    void deleteById(Integer id);
}
