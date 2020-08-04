package com.jose;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        House house = new House("Casa de Pepe", "House0000000001");
        User user = new User("Pepe", "Mendez", "jaunojse.jose", "jajajaj", 1);
        User user2 = new User("Ignacio", "Suarez", "zzxcccc", "yeiyeia", 1);
        User user3 = new User("Penelope", "Martinez", "asdasdas", "okokok", 1);
        User user4 = new User("Jania", "Lopez", "qqqqqqq", "aaazzz", 1);
        List<User> users  = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        users.add(user4);
        Device device = new Device("Sala",1,users);

        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            
            manager.persist(device);
            transaction.commit();
            System.out.printf("se ha añadido con exito");
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }
}
