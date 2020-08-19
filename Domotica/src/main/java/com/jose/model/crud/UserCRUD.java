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

    public static  boolean exitsUser(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

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
            manager.close();
            return exits;
        }
    }

    public static  boolean exitsUserByEmailAndHouseID(String email, int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean exits = false;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :userEmail and u.id_house = :houseID")
                    .setParameter("userEmail",email)
                    .setParameter("houseID", houseID);
            User user = (User) query.getResultList().get(0);
            System.out.println(user);

            if(user != null){
                exits = true;
            }

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return exits;
        }
    }

    public static User getUser(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :userEmail ")
                    .setParameter("userEmail",email);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return user;
        }
    }

    public static User getUser(int userID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
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
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
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
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.println("Se Agreo correctamente");

        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Algun error: " + e);
            throw e;
        }finally {
            manager.close();
        }
    }

    public static void update(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.println("Se actualizo correctamente");

        } catch (Exception e) {
            transaction.rollback();
            System.out.println("algun error: " + e);
            throw e;
        }finally {
            manager.close();
        }
    }

    public static void delete(User user) {
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM User  s where s.ID = :userID")
                    .setParameter("userID", user.getID()).executeUpdate();
            System.out.println(delete);
            transaction.commit();
            System.out.println("Se borro correctamente");

        } catch (Exception e) {
            transaction.rollback();
            System.out.printf("Algun error: " + e);
            throw e;
        }finally {
            manager.close();
        }
    }

    public static int getNumbersOfUserFromHouse(int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
        int numbersOfUsers = -1;
        try {
//            transaction.begin();
            Query query = manager.createQuery("SELECT s FROM User  s where s.id_house = :houseID")
                    .setParameter("houseID", houseID);
            numbersOfUsers = query.getResultList().size();

        } catch (PersistenceException e) {
//            transaction.rollback();
            throw e;
        } finally {
            manager.close();
            return numbersOfUsers;
        }

    }
}
