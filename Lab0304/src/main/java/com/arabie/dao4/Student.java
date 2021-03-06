package com.arabie.dao4;// default package
// Generated Mar 21, 2021, 2:20:28 PM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;

/**
 * Student generated by hbm2java
 */
//@Entity
//@Table(name = "student")
//@DiscriminatorValue("student")
//@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person implements java.io.Serializable {

    //    private Integer id;
//    @Column(name = "department")

    private String department;

    public Student() {
    }

    public Student(String firstName, String lastName, String department) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.department = department;
    }

//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "department='" + department + '\'' +
                "} " + super.toString();
    }
}


