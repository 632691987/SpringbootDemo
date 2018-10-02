package com.springboot.demo001.bean.yaml;

import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@PropertySource(value={"classpath:person3.properties"})
@Component
@ConfigurationProperties(prefix = "person555")//默认是从全局变量中获取值的,但可以结合PropertySource
public class Person3 {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,String> maps;
    private List<Object> lists;
    private Dog dog;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lastName", lastName)
                .add("age", age)
                .add("boss", boss)
                .add("birth", birth)
                .add("maps", maps)
                .add("lists", lists)
                .add("dog", dog)
                .toString();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
