package com.arabie;

import com.arabie.dao.*;

import javax.persistence.criteria.*;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.*;

import static java.lang.System.*;


public class App {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Seller.class).createCriteria("user")
                .add(Restrictions.eq("userName", "ahmed2"));
        criteria.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(Product.class)
                .add(Restrictions.between("quantity", 10, 11))
                .list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(Product.class)
                .add(Restrictions.gt("quantity", 11)).list()
                .stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String[] emails = {"a@a.com", "mail@gmail.com"};
        session.createCriteria(User.class)
                .add(Restrictions.in("email", emails))
                .list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .add(Restrictions.isNull("email"))
                .list()
                .stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .add(Restrictions.isNotNull("email"))
                .list()
                .stream().forEach(out::println);
        ;

        out.println("--------------------------------------------------");
        session.createCriteria(Seller.class)
                .add(Restrictions.isEmpty("products"))
                .list()
                .stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(Seller.class)
                .add(Restrictions.sizeGt("products", 1))
                .list()
                .stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .add(Restrictions.eq("fullName", "Ahmed arabie")
                        .ignoreCase())
                .list()
                .stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .add(Restrictions.or(
                        Restrictions.and(
                                Restrictions.like("fullName", "a%"),
                                Restrictions.like("userName", "b%")
                        ),
                        Restrictions.in("email", emails)
                )).list().stream().forEach(out::println);

//        out.println("--------------------------------------------------");
//        DetachedCriteria subQuery =
//                DetachedCriteria.forClass(Product.class, "p");
//        subQuery = subQuery.add(Restrictions.eq("p.seller.id", "s.id"))
//                .add(Restrictions.isNotNull("p.buyerBidProducts"))
////                .setProjection(Property.forName("p.id").count());
//                .setProjection(Projections.count("p.id"));
//
//        session.createCriteria(Seller.class, "s")
//                .add(Subqueries.gt(2L, subQuery))
//                .list().stream().forEach(out::println);
//
        out.println("--------------------------------------------------");
        session.createCriteria(Seller.class)
                .createAlias("products", "p")
                .add(Restrictions.like("value", "%seller%"))
                .add( Restrictions.like("p.name", "%hp%"))
                .list()
                .stream()
                .forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .setProjection(
                        Projections.projectionList()
                        .add(Projections.id())
                        .add(Projections.property("email"))
                        .add(Projections.property("userName")))
                .list()
                .stream()
                .forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(User.class)
                .setProjection(Projections.rowCount()).list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        session.createCriteria(Seller.class)
                .createAlias ("products", "p").setProjection(
                Projections.projectionList()
                        .add(Property.forName("id").group())
                        .add(Property.forName("p.name").group())
                        .add(Property.forName("p.manufacturingName").group())
                        .add(Property.forName("p.buyerBidProducts").count())
        ).list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        User user = new User();
        user.setFullName("ahmed arabie");
        Example exampleUser = Example.create(user)
                .ignoreCase().enableLike(MatchMode.ANYWHERE)
                .excludeProperty("password");

        session.createCriteria(User.class)
                .add(exampleUser).createCriteria ("seller")
//                .add(Restrictions.isNull("products"))
                .list().stream().forEach(out::println);
        session.close();
    }


}
