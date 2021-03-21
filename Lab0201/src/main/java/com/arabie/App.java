package com.arabie;

import com.arabie.dao.*;
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
//        Session session1 = sessionFactory.openSession();
        User user1 = new User("a@a.com", "quesna", new Date(), "ahmed1", "1234");
        User user2 = new User("a1@a.com", "quesna", new Date(), "ahmed2", "1234");
        Seller seller = new Seller(user1, "sellerValue");
        Buyer buyer = new Buyer(user2, "buyerValue");
        Product product1 = new Product("product1", "manufacturingName", new Date(), 12, new Date(), new Date());
        Category category = new Category("cat1");


        session.beginTransaction();
//        session1.beginTransaction();

        session.persist(user1);
        session.persist(user2);
        session.persist(buyer);
        session.persist(seller);
//        session1.persist(seller);
        session.persist(category);
        session.persist(product1);

        session.getTransaction().commit();

        session.beginTransaction();

        category.getProducts().add(product1);

        category.setDescription("cat1 desc");
        BuyerBidProductId buyerBidProductId = new BuyerBidProductId(buyer.getId(), product1.getId());
        BuyerBidProduct buyerBidProduct = new BuyerBidProduct(buyerBidProductId, buyer, product1, new Date(), 10, 10);
        BuyerBuyProductId buyerBuyProductId = new BuyerBuyProductId(buyer.getId(), product1.getId());
        BuyerBuyProduct buyerBuyProduct = new BuyerBuyProduct(buyerBuyProductId, buyer, product1, new Date(), 10, 10);
        product1.setSeller(seller);
        product1.getBuyerBidProducts().add(buyerBidProduct);
        product1.getBuyerBuyProducts().add(buyerBuyProduct);
        product1.setExpirationDate(new Date());

        session.persist(buyerBidProduct);
        session.persist(buyerBuyProduct);
        session.persist(category);
        session.persist(product1);
//        System.out.println(seller1.getProducts().size());
//        System.out.println(seller1);
        session.getTransaction().commit();
//        Seller seller1 = session.load(Seller.class,1);
//        Seller seller2 = session1.load(Seller.class,1);
//        System.out.println(seller1.getProducts());
//        System.out.println(seller2.getProducts());
        session.close();
//        session1.close();
        System.out.println("Insertion Done");
    }


}
