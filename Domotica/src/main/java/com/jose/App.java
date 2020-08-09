package com.jose;

import com.jose.controller.Controller;
import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.crud.HouseCRUD;
import com.jose.model.crud.HouseCodeCRUD;
import com.jose.model.crud.UserCRUD;
import com.jose.model.operations.UserFunctions;
import com.jose.model.schemas.*;
import com.jose.view.LogRegView.LogHomeView;
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
        Controller app = new Controller();

        /*
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            manager.persist(user);

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
