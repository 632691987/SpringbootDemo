package com.springboot.demo001.adviced.service;

import com.springboot.demo001.adviced.bean.Employee;
import com.springboot.demo001.adviced.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 要指定cache 组件名字和 里面的 key-value 里面得 key
     *
     * 这里指定的 key 是有技巧得，可以有如何几种：
     *
     * #root.methodName, 当前被调用的方法名
     * #root.target,     当前被调用的目标对想
     * #root.args[0],    当前被调用方法的参数列表
     * #result ---> 这个很特别，代表了结果, 用于 condition 和 unless
     *
     * #id 参数 id 的值,这样等同于 #a0, #p0, #root.args[0]
     *
     * 也可以指定 keyGenerator, 但是 key 和 keyGenerator 只能 2选1
     *
     * condition 指定符合制定得情况下才缓存, 那么缓存
     * unless    或者除非什么时候为true，那么不缓存
     *
     *
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}, key = "#root.methodName+'['+#id+']'")
    public Optional<Employee> getEmp(Integer id) {
        logger.info("Function {}, parameter is {} runned", "com.springboot.demo001.adviced.service.EmployeeService.getEmp", id);
        return employeeRepository.findById(id);
    }

    @Cacheable(cacheNames = {"emp"}, keyGenerator = "SysKeyGenerator")
    public Optional<Employee> getEmp2(Integer id) {
        logger.info("Function {}, parameter is {} runned", "com.springboot.demo001.adviced.service.EmployeeService.getEmp2", id);
        return employeeRepository.findById(id);
    }

    /**
     *
     * 只有 id > 1 才进行缓存
     *
     */
    @Cacheable(cacheNames = {"emp"}, keyGenerator = "SysKeyGenerator", condition = "#id > 1")
    public Optional<Employee> getEmp3(Integer id) {
        logger.info("Function {}, parameter is {} runned", "com.springboot.demo001.adviced.service.EmployeeService.getEmp3", id);
        return employeeRepository.findById(id);
    }

    /**
     * 除非什么条件下就不缓存，例如下面就是不缓存 id =1
     */
    @Cacheable(cacheNames = {"emp"}, keyGenerator = "SysKeyGenerator", unless = "#id == 1")
    public Optional<Employee> getEmp4(Integer id) {
        logger.info("Function {}, parameter is {} runned", "com.springboot.demo001.adviced.service.EmployeeService.getEmp4", id);
        return employeeRepository.findById(id);
    }

    /**
     * 异步模式下 unless 是不支持的
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}, keyGenerator = "SysKeyGenerator", sync = true)
    public Optional<Employee> getEmp5(Integer id) {
        logger.info("Function {}, parameter is {} runned", "com.springboot.demo001.adviced.service.EmployeeService.getEmp5", id);
        return employeeRepository.findById(id);
    }

}
