package com.springboot.demo001;


import com.springboot.demo001.adviced.cache.bean.Employee;
import com.springboot.demo001.adviced.cache.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisPOC {

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 操作字符串

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate; // k-v 对象的

    @Autowired
    @Qualifier("employeeRedisTemplate")
    private RedisTemplate<Object, Employee> employeeRedisTemplate;

    /**
     *
     * Redis 常见的 5 大数据类型
     * String, list, set, hash, zset
     *
     *
     * stringRedisTemplate.opsForValue()    [String(字符串)]
     * stringRedisTemplate.opsForList()     [List(列表)]
     * stringRedisTemplate.opsForSet()      [Set(集合)]
     * stringRedisTemplate.opsForHash()     [Hash(散列)]
     * stringRedisTemplate.opsForZSet()     [Zset(有序集合)]
     *
     *
     */
    @Test
    public void string_append() {
        stringRedisTemplate.opsForValue().append("message", "hello");
    }

    @Test
    public void string_get() {
        String message = stringRedisTemplate.opsForValue().get("message");
        System.out.println(message);
    }

    @Test
    public void list_leftPush() {
        stringRedisTemplate.opsForList().leftPush("myList", "1");
        stringRedisTemplate.opsForList().leftPush("myList", "2");
    }

    @Test
    public void string_for_employee() {
        Employee employee = employeeRepository.findById(2).get();
        employeeRedisTemplate.opsForValue().set("emp-01", employee);
    }

}
