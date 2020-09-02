package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;

public class UserCRUD {
    /*
    Work: refactor code, much repeated code and try catch
    comments: queries don't need transaction and begin, can close the entityManager (recommended)
              can commit an object? test. passed
     */

    public static  boolean exitsUserByEmailAndHouseID(String email, int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
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
        } finally {
            manager.close();
            return exits;
        }
    }

    public static User getUserByEmail(String email,int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :userEmail and u.id_house = :houseID")
                    .setParameter("userEmail",email).setParameter("houseID", houseID);;
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return user;
        }
    }

    public static User getUserByID(int userID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.ID = :userID ")
                    .setParameter("userID",userID);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return user;
        }
    }

    public static ArrayList<User> getUserByEmailAndPassword(String email, String password){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<User> users = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :email and u.password =: password ")
                    .setParameter("email",email)
                    .setParameter("password", password);
            users = (ArrayList<User>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return users;
        }
    }

    public static User getUserByEmailAndPasswordAndHouseID(String email, String password, int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = null;
        try {
            Query query = manager.createQuery("Select u From User u WHERE u.email = :email and u.password =: password " +
                    "and u.id_house = :houseID ")
                    .setParameter("email",email)
                    .setParameter("password", password)
                    .setParameter("houseID", houseID);
            user = (User) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return user;
        }
    }

    public static int getNumbersOfUserFromHouse(int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        int numbersOfUsers = -1;
        try {
            Query query = manager.createQuery("SELECT s FROM User  s where s.id_house = :houseID")
                    .setParameter("houseID", houseID);
            numbersOfUsers = query.getResultList().size();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            manager.close();
            return numbersOfUsers;
        }

    }

    public static ArrayList<User> getUsersInHouse(int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<User> users = new ArrayList<>();
        try {
            Query query = manager.createQuery("SELECT s FROM User  s where s.id_house = :houseID and not s.userRole =:userRole")
                    .setParameter("houseID", houseID)
                    .setParameter("userRole", UserRole.INVITED);
            users = (ArrayList<User>) query.getResultList();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            manager.close();
            return users;
        }

    }

    public static ArrayList<User> getUsersInHouseAndNoDevice(int houseID, int deviceID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> finalUsers = new ArrayList<>();
        try {
            Query query = manager.createQuery("SELECT s FROM User s WHERE s.id_house = :houseID " +
                    "AND NOT s.userRole = :userRole")
                    .setParameter("houseID", houseID)
                    .setParameter("userRole", UserRole.INVITED);
            users = (ArrayList<User>) query.getResultList();
            Device d =DeviceCRUD.getDeviceByID(deviceID);
            finalUsers = users;
            for(Iterator<User> iterator = users.iterator(); iterator.hasNext();){
                User user = iterator.next();
                for(Device device : user.getDevices()){
                    if(d.getID() == device.getID()) iterator.remove();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            manager.close();
            return finalUsers;
        }

    }

    public static ArrayList<User> getUsersInDevice(int deviceID, int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<User> users = new ArrayList<>();
        try {
            Query query = manager.createQuery("SELECT s FROM User  s Join s.devices ud where s.id_house = :houseID and ud.ID = :deviceID")
                    .setParameter("houseID", houseID)
                    .setParameter("deviceID", deviceID);
            users = (ArrayList<User>) query.getResultList();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            manager.close();
            return users;
        }

    }

    public static void create(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        System.out.println("XXXXX");
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.println("Se Agreo correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Algun error: " + e);
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
        }finally {
            manager.close();
        }
    }


}
