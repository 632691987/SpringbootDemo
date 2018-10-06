package com.springboot.demo001.adviced.bean;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private Integer gender;

    @ManyToOne
    @JoinColumn(name="dId", nullable=false)
    private Department department;

    public Employee() {
        super();
    }

    public Employee(String lastName, String email, Integer gender, Department department) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equal(id, employee.id) &&
                Objects.equal(lastName, employee.lastName) &&
                Objects.equal(email, employee.email) &&
                Objects.equal(gender, employee.gender) &&
                Objects.equal(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, lastName, email, gender, department);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("lastName", lastName)
                .add("email", email)
                .add("gender", gender)
                .add("department", department)
                .toString();
    }
}
