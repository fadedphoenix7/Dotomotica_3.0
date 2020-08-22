package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.House;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class HouseCRUD {

    public static void create(House house){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(house);
            transaction.commit();
            System.out.println("Se Agreo correctamente");

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static boolean exitsHouseByCodeID(int codeID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        boolean exitsHouse = false;

        try {
            Query query = manager.createQuery("Select h From House h WHERE h.registrationCodeID = :code")
                    .setParameter("code",codeID);
            House house =  (House) query.getResultList().get(0);
            if(house != null) exitsHouse = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return !exitsHouse;
        }
    }

    public static boolean exitsHouseByRegisterCode(String registerCode){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        boolean existHouse = false;

        try {
            Query query = manager.createQuery("Select h.ID From House h WHERE h.codeToRegister = :code")
                    .setParameter("code",registerCode);
            if(query.getResultList().size() > 0 ){
                existHouse = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return existHouse;
        }
    }

    public static House getHouseByRegisterCode(String registerCode){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        House house = null;

        try {
            Query query = manager.createQuery("Select h From House h WHERE h.codeToRegister = :code")
                    .setParameter("code",registerCode);
            System.out.println(query.getResultList());
           house = (House) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return house;
        }
    }

    public static int exitsHouseByCode(String houseCodeRegister){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        int registerCdoeID = -1;

        try {
            Query query = manager.createQuery("Select h.ID From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",houseCodeRegister);
            registerCdoeID = (int) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return registerCdoeID;
        }
    }

    public static House getHouseByID(int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        House house = new House();

        try {
            Query query = manager.createQuery("Select h From House h WHERE h.ID = :houseID")
                    .setParameter("houseID",houseID);
            house = (House) query.getResultList().get(0);
            System.out.println(house);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return house;
        }
    }

    public static void update(House house){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(house);
            transaction.commit();
            System.out.println("Se Actualizo correctamente");

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void delete(House house){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM House  h where h.ID = :houseID")
                        .setParameter("houseID", house.getID()).executeUpdate();
            System.out.println(delete);
            transaction.commit();
            System.out.println("Se borro correctamente");

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

}
