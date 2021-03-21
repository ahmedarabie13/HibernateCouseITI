package com.arabie;

import com.arabie.dao1.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;


public class App {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //inheritance 1
        Person person1 = new Person("ahmed", "arabie");
        Person person2 = new Person("ahmed", "jamal");
        Student student = new Student("ahmed", "arabie", "jets");
        Teacher teacher = new Teacher("ahmed", "jamal", new Date());

        session.beginTransaction();
        session.persist(person1);
        session.persist(person2);
        session.persist(student);
        session.persist(teacher);

        session.getTransaction().commit();

        session.close();
        System.out.println("Insertion Done");
    }


}
