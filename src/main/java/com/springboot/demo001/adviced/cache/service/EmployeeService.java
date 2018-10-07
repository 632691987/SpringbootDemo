package com.springboot.demo001.adviced.cache.service;

import com.springboot.demo001.adviced.cache.bean.Employee;
import com.springboot.demo001.adviced.cache.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"emp"})/**CacheConfig 抽取缓存的公共配置**/
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
        logger.info("Function {}, parameter is {} runned", "getEmp", id);
        return employeeRepository.findById(id);
    }

    @Cacheable(keyGenerator = "SysKeyGenerator")
    public Optional<Employee> getEmp2(Integer id) {
        logger.info("Function {}, parameter is {} runned", "getEmp2", id);
        return employeeRepository.findById(id);
    }

    /**
     *
     * 只有 id > 1 才进行缓存
     *
     */
    @Cacheable(keyGenerator = "SysKeyGenerator", condition = "#id > 1")
    public Optional<Employee> getEmp3(Integer id) {
        logger.info("Function {}, parameter is {} runned", "getEmp3", id);
        return employeeRepository.findById(id);
    }

    /**
     * 除非什么条件下就不缓存，例如下面就是不缓存 id =1
     */
    @Cacheable(keyGenerator = "SysKeyGenerator", unless = "#id == 1")
    public Optional<Employee> getEmp4(Integer id) {
        logger.info("Function {}, parameter is {} runned", "getEmp4", id);
        return employeeRepository.findById(id);
    }

    /**
     *
     *
     * 异步模式下 unless 是不支持的
     *
     *
     */
    @Cacheable(keyGenerator = "SysKeyGenerator", sync = true)
    public Optional<Employee> getEmp5(Integer id) {
        logger.info("Function {}, parameter is {} runned", "getEmp5", id);
        return employeeRepository.findById(id);
    }

    @Cacheable(key = "'employee'+#id")
    public Optional<Employee> getEmp6(Integer id) {
        logger.info("Function {}, parameter is {} runned", "getEmp6", id);
        return employeeRepository.findById(id);
    }


    /**
     *
     *
     * 注意一定要有返回值，这样cache 才能能够获取到
     *
     *
     */
    @CachePut(key = "'employee'+#employee.id")
    public Employee updateEmployee(Employee employee) {
        logger.info("Function {}, parameter is {} runned", "updateEmployee", employee);
        this.employeeRepository.save(employee);
        return employee;
    }

    /**
     *
     * CacheEvict 有个 option, 叫 beforeInvocation, 是说在方法执行之前执行的，那就避免了执行删除错误方法也能删除
     */
    @CacheEvict(key = "'employee'+#id")
    public void deleteEmployee(Integer id) {
        logger.info("Function {}, parameter is {} runned", "deleteEmployee", id);
        //this.employeeRepository.deleteById(id);
    }


    /**
     *
     * 注意了，这个方法即使执行多少次都是会执行得，原因在于里面有个 CachePut
     *
     */
    @Caching(
            cacheable = {
                    @Cacheable(key="'employee'+#lastName")
            },
            put = {
                    @CachePut(key = "'employee'+#result.id")
            }
    )
    public Employee getEmployeeByName(String lastName) {
        logger.info("Function {}, parameter is {} runned", "getEmployeeByName", lastName);
        return this.employeeRepository.findByLastName(lastName).get();
    }

}
