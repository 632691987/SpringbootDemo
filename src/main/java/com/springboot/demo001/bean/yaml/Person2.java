package com.springboot.demo001.bean.yaml;

import com.google.common.base.MoreObjects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 告诉SpringBoot 本类中所有属性都和配置文件中相关得属性进行绑定
 * 注意，用 @Value 的说话，不支持松散绑定，不支持 JSR303 数据校验
 * 但是它能支持 SpEL 表达式，另外是它能够随时用一个 @Value("${person444.last-name}") 出现在任何属于 @Component 的类中
 */
@Component
public class Person2 {

    @Value("${person444.last-name}")
    private String lastName;

    @Value("#{11*2}")
    private Integer age;

    @Value("true")
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
