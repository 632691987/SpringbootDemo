package com.springboot.demo001;

import com.springboot.demo001.adviced.cache.bean.Department;
import com.springboot.demo001.adviced.cache.bean.Employee;
import com.springboot.demo001.adviced.cache.repository.DepartmentRepository;
import com.springboot.demo001.adviced.cache.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAdviceRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {
        Optional<Employee> employeeOptional = employeeRepository.findById(1);
        if(employeeOptional.isPresent()) {
            System.out.println(employeeOptional.get());
        }

        List<Department> departmentList = departmentRepository.findAll();
        Assert.assertTrue(!departmentList.isEmpty());
    }

}
