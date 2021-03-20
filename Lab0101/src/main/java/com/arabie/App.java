package com.arabie;

import com.arabie.dao.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Account account = new Account();
        account.setUserName("Arabie3");
        account.setFullName("Ahmed Arabie");
        account.setPhone("01275576924");
        account.setAddress("Menofia");
        account.setPassword("1420");
        account.setBirthday(new Date());

        session.beginTransaction();

        session.save(account);

        session.getTransaction().commit();

        System.out.println("Insertion Done");
    }


}
