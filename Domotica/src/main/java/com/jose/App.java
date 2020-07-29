package com.jose;

import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        House house = new House(1,"Casa de Pepe", "House0000000001");
        User user = new User(1,"Jose", "Mendez", "pepelotas.jose", "pepeok", house.getID());

        Configuration con = new Configuration().configure().addAnnotatedClass(User.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
       
        session.save(house);
        session.save(user);
        tx.commit();
        session.close();
    }
}
