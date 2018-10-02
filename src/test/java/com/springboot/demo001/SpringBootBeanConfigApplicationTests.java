package com.springboot.demo001;

import com.springboot.demo001.bean.yaml.Person;
import com.springboot.demo001.bean.yaml.Person2;
import com.springboot.demo001.bean.yaml.Person3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 留意Package的位置，一定和 HelloWorldMainApplication 类一样，处于最顶端的
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBeanConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private Person2 person2;

    @Autowired
    private Person3 person3;

    @Autowired
    private ApplicationContext ioc;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testIfPersonHasValue() {
        assertThat(person).isNotNull();
        assertThat(person).matches(p -> p.getAge() == 36);
        assertThat(person).matches(p -> p.getLastName().equalsIgnoreCase("zhangsan"));
        assertThat(person).matches(p -> p.getLists().contains("lisi".concat(p.getLastName())));
        logger.info("{} run finish", "testIfPersonHasValue");
    }

    @Test
    public void testIfPerson2HasValue() {
        assertThat(person2).isNotNull();
        assertThat(person2).matches(p -> p.getAge() == 22);
        assertThat(person2).matches(p -> p.getLastName().equalsIgnoreCase("xxmmhhkk"));
        assertThat(person2).matches(p -> p.getBoss().booleanValue() == true);
        logger.info("{} run finish", "testIfPerson2HasValue");
    }

    @Test
    public void testIfPerson3HasValue() {
        assertThat(person3).isNotNull();
        assertThat(person3).matches(p -> p.getAge() == 65);
        assertThat(person3).matches(p -> p.getLastName().equalsIgnoreCase("xxmmhhkk"));
        assertThat(person3).matches(p -> p.getBoss().booleanValue() == false);
        logger.info("{} run finish", "testIfPerson3HasValue");
    }

    @Test
    public void testIfContainHelloService() {
        assertThat(ioc).matches(i -> i.containsBean("helloService"));
        logger.info("{} run finish", "testIfContainHelloService");
    }

}
