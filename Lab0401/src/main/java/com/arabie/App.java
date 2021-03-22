package com.arabie;

import com.arabie.dao.BuyerBidProduct;
import com.arabie.dao.Product;
import com.arabie.dao.Seller;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static java.lang.System.*;
import com.arabie.models.*;
import java.util.Date;
import java.util.Iterator;


public class App {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        var query1 = session.createQuery("from Seller s where s.value= :val ");
        query1.setString("val","hi");

        var list = query1.list();
        list.stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString = "from Product p where p.name like :name and p.manufacturingDate < :date";
        Query q = session.createQuery(queryString)
                .setString("name", "product2")
                .setDate("date", new Date(1990,1,1));
        q.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        Seller seller = session.get(Seller.class,1);
        Product product1 =session.get(Product.class,2);
        String queryString1 = "from Product p where p.name like ? and p.manufacturingDate > ?";
        Query q1 = session.createQuery("from User u where u.seller = :sellerKey").setEntity("sellerKey",
        seller);
        q1.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString3 = "from Product p where p.name like 'p1' ";
        Query q2 = session.createQuery(queryString3);
        q2.list().stream().forEach(out::println);
        out.println("--------------------------------------------------");
        String queryString4 ="from Product p where p.name like '\\%p%' escape '\'";
        q2 = session.createQuery(queryString4);

        q2.list().stream().forEach(out::println);
        out.println("--------------------------------------------------");
        String queryString5 ="from Product p where p.name like 'H%'";
        q2 = session.createQuery(queryString5);

        q2.list().stream().forEach(out::println);
        out.println("--------------------------------------------------");
        String queryString6 ="from Product p where p.categories is not empty ";
        q2 = session.createQuery(queryString6);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString7 ="from Product p where upper(p.name) like 'H%' or p.manufacturingName like 'C%' ";
        q2 = session.createQuery(queryString7);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString8 ="from Product p where size(p.categories) = 1";
        q2 = session.createQuery(queryString8);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString9 ="from User u order by u.userName  asc ,u.fullName desc ";
        q2 = session.createQuery(queryString9);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString10 ="from Product p, BuyerBidProduct bid";
        q2 = session.createQuery(queryString10);
//        q2.list().stream().forEach(out::println);
        Iterator pairs = q2.list().iterator();
        while ( pairs.hasNext() ) {
            Object[] pair = (Object[]) pairs.next();
            Product product = (Product) pair[0];
            BuyerBidProduct bid = (BuyerBidProduct) pair[1];
            out.println(product);
            out.println(bid);
        }

        out.println("--------------------------------------------------");
        String queryString11 ="select count(p.seller) from Product p";
        q2 = session.createQuery(queryString11);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString12 ="select sum(buy.amount) from BuyerBuyProduct buy";
        q2 = session.createQuery(queryString12);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString13 ="from Seller s where s.user.email = 'mail@gmail.com'";
        q2 = session.createQuery(queryString13);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString14 ="from Product p join p.seller s where s.value like '%se%' and p.name like '%pr%'";
        q2 = session.createQuery(queryString14);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString15 ="from Product p left join p.seller s with s.value like '%se%' where p.name like '%pr%'";
        q2 = session.createQuery(queryString15);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString16 ="from Product p, Seller s where p.seller = s and s.value like '%se%'";
        q2 = session.createQuery(queryString16);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString17 ="from Product p join p.seller s where s.value like '%se%' and p.name like '%t%'";
        q2 = session.createQuery(queryString17);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString18 ="select b.value, size(b.buyerBuyProducts) from Buyer b group by b.value";
        q2 = session.createQuery(queryString18);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString19 ="select b.value, size(b.buyerBuyProducts) from Buyer b group by b.value having b.value like '%bu%'";
        q2 = session.createQuery(queryString19);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString20 ="select s.value, size(s.products) from Seller s group by s.value HAVING s.value = 'sellerValue'";
        q2 = session.createQuery(queryString20);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString21 ="select new com.arabie.models.AbstractBuyedProduct(p.name, size(p.buyerBuyProducts), p.manufacturingDate) from Product p where p.buyerBuyProducts is not empty";
        q2 = session.createQuery(queryString21);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString22 ="from Product p where p.seller = some (from Seller s where s.value like '%se%')";
        q2 = session.createQuery(queryString22);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString23 ="from Product p where p.seller = any (from Seller s where s.value like '%se%')";
        q2 = session.createQuery(queryString23);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString24 ="from Product p where p.seller = all (from Seller s where s.value like '%se%')";
        q2 = session.createQuery(queryString24);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString25 ="from Product p where p.seller  in (from Seller s where s.value like '%se%')";
        q2 = session.createQuery(queryString25);
        q2.list().stream().forEach(out::println);

        out.println("--------------------------------------------------");
        String queryString26 ="from Seller s where :givenProduct in (select p from s.products p)";
        q2 = session.createQuery(queryString26).setEntity("givenProduct", product1);
        q2.list().stream().forEach(out::println);








        session.close();
        out.println("Query Done");
    }


}
