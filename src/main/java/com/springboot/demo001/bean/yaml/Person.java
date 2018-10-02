package com.springboot.demo001.bean.yaml;

import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 告诉SpringBoot 本类中所有属性都和配置文件中相关得属性进行绑定
 *
 * @ConfigurationProperties 可以批量处理属性，并且支持松散语法绑定，
 * 即： 如果配置文件里面是 last-name, 那么照样录入类的到 lastName 属性中，并且支持 JSR303 数据校验
 * 唯一的缺点是不支持 SpEL
 *
 * 注意，这里得 person2333 因为没有 @PropertySource(value={"classpath:person3.properties"})
 * 因此一定会在default 处
 */
@Component
@ConfigurationProperties(prefix = "person2333")//默认是从全局变量中获取值的
@Validated
public class Person {

    private String lastName;

    @Positive
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
