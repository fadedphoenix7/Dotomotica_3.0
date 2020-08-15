package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class UserCRUD {

    private static EntityManager manager = EMFBootstrapper.openEntityManager();
    private static EntityTransaction transaction = manager.getTransaction();


    public static  boolean exitsUser(String email){
        boolean exits = false;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :userEmail")
                    .setParameter("userEmail",email);
            User user = (User) query.getResultList().get(0);
            System.out.println(user);

            if(user != null){
                exits = true;
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return exits;
        }
    }

    public static User getUser(String email){
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :userEmail ")
                    .setParameter("userEmail",email);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return user;
        }
    }

    public static User getUser(int userID){
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.ID = :userID ")
                    .setParameter("userID",userID);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return user;
        }
    }

    public static User getUser(String email, String password){
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :email and u.password =: password ")
                    .setParameter("email",email)
                    .setParameter("password", password);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return user;
        }
    }

    public static void create(User user){
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.println("Se Agreo correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            //manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void update(User user){
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.println("Se actualizo correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            //manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void delete(User user){
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM User  s where s.ID = :userID")
                    .setParameter("userID", user.getID()).executeUpdate();
            System.out.println(delete);
            transaction.commit();
            System.out.println("Se borro correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            //manager.close();
        }
        System.out.println( "Complete!" );
    }
}
