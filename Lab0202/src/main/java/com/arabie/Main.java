package com.arabie;

import com.arabie.dao.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(standardRegistry).buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        User user1 =new User("a1@a.com","quesna","1234","1234",Date.valueOf(LocalDate.of(1997,3,13)),Date.valueOf(LocalDate.of(1997,3,13)),"arabie","1234","ahmed arabie");
        User user2 =new User("a2@a.com","quesna","1234","1234",Date.valueOf(LocalDate.of(1994,11,26)),Date.valueOf(LocalDate.of(1997,3,13)),"arabie","1234","ahmed arabie");
        Seller seller =new Seller(user1,"seller Value");
        Product product = new Product("desc1",seller,"p1","p",Date.valueOf(LocalDate.of(1997,3,13)),1500,Date.valueOf(LocalDate.of(1997,3,13)),Date.valueOf(LocalDate.of(2010,11,26)),Date.valueOf(LocalDate.of(1997,3,13)));
        Product product2 = new Product("desc2",seller,"p2","p",Date.valueOf(LocalDate.of(1997,3,13)),1500,Date.valueOf(LocalDate.of(1997,3,13)),Date.valueOf(LocalDate.of(2010,11,26)),Date.valueOf(LocalDate.of(1997,3,13)));
        Set<Product>products = new HashSet<>();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        products.add(product);
        products.add(product2);
        Category category = new Category("cat1","cat1 desc" ,products);
        Buyer buyer = new Buyer(user2,"buyer1");


        session.save(user1);
        session.save(user2);
        session.save(seller);
        session.save(product);
        session.save(product2);
        session.save(category);
        session.save(buyer);
        BuyerBuyProductId buyerBuyProductId = new BuyerBuyProductId(buyer.getId(),product.getId());
        BuyerBuyProduct buyerBuyProduct = new BuyerBuyProduct(buyerBuyProductId,buyer,product,Date.valueOf(LocalDate.of(1994,11,26)),15f,15);
        BuyerBidProductId buyerBidProductId = new BuyerBidProductId(buyer.getId(),product.getId());
        BuyerBidProduct buyerBidProduct = new BuyerBidProduct(buyerBidProductId,buyer,product,Date.valueOf(LocalDate.of(1994,11,26)),15f,12);

        session.save(buyerBuyProduct);
        session.save(buyerBidProduct);
        session.getTransaction().commit();
        session.close();

    }
}
