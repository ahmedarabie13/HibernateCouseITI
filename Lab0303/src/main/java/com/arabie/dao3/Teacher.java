package com.arabie.dao3;// default package
// Generated Mar 21, 2021, 2:20:28 PM by Hibernate Tools 6.0.0.Alpha2


import java.util.Date;

/**
 * Teacher generated by hbm2java
 */
public class Teacher  extends Person implements java.io.Serializable {


//     private Integer id;
     private Date hireDate;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, Date hireDate) {
       super.setFirstName(firstName);
       super.setLastName(lastName);
       this.hireDate = hireDate;
    }
//
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
    public Date getHireDate() {
        return this.hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }




}


