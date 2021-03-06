package com.arabie.dao4;// default package
// Generated Mar 21, 2021, 2:20:28 PM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;

/**
 * Person generated by hbm2java
 */
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//@DiscriminatorColumn(name = "person_type")
//@DiscriminatorValue("person")
//@Table(name = "person")
public class Person  implements java.io.Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
//    @Column(name = "first_name")
     private String firstName;
//    @Column(name = "last_name")
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
       this.firstName = firstName;
       this.lastName = lastName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}


