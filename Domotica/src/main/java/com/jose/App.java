package com.jose;

import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.crud.HouseCRUD;
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
        HouseCRUD c = new HouseCRUD();
        c.delete(1);
        /*
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            //manager.persist(device);
            transaction.commit();
            System.out.printf("se ha añadido con exito");
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );*/
    }
}
