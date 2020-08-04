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

        House house = new House(1,"Casa de Pepe", "House0000000001");
        User user = new User(2,"Pepe", "Mendez", "jaunojse.jose", "jajajaj", house.getID());
        User user2 = new User(2,"Pepe", "Mendez", "jaunojse.jose", "jajajaj", house.getID());
        User user3 = new User(2,"Pepe", "Mendez", "jaunojse.jose", "jajajaj", house.getID());
        User user4 = new User(2,"Pepe", "Mendez", "jaunojse.jose", "jajajaj", house.getID());
        List<User> users  = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        users.add(user4);
        Device device = new Device(1,"Foco",1,users);

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
