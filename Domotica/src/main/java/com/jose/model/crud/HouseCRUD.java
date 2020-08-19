package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.House;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class HouseCRUD {

    private static EntityManager manager = EMFBootstrapper.openEntityManager();
    private static EntityTransaction transaction = manager.getTransaction();

    public void create(String name, String code, String registerCode){
        try {
            transaction.begin();
            int registrationCodeID = exitsHouseByCode(registerCode);
            if(registrationCodeID >= 0){
                House house = new House();
                house.setName(name);
                house.setCode(code);
                house.setRegistrationCodeID(registrationCodeID);
                manager.persist(house);
                transaction.commit();
                System.out.println("Se Agreo correctamente");
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            //manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static boolean exitsHouseByCodeID(int codeID){
        boolean exitsHouse = false;
        try {
            Query query = manager.createQuery("Select h From House h WHERE h.registrationCodeID = :code")
                    .setParameter("code",codeID);
            House house =  (House) query.getResultList().get(0);
            if(house != null) exitsHouse = true;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return !exitsHouse;
        }
    }

    public static int exitsHouseByRegisterCode(String registerCode){
        int registerCdoeID = -1;
        try {
            Query query = manager.createQuery("Select h.ID From House h WHERE h.code = :code")
                    .setParameter("code",registerCode);
            registerCdoeID = (int) query.getResultList().get(0);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return registerCdoeID;
        }
    }

    public static int exitsHouseByCode(String houseCodeRegister){
        int registerCdoeID = -1;
        try {
            Query query = manager.createQuery("Select h.ID From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",houseCodeRegister);
            registerCdoeID = (int) query.getResultList().get(0);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return registerCdoeID;
        }
    }

    public static House getHouse(int houseID){
        House house = new House();
        try {
            Query query = manager.createQuery("Select h From House h WHERE h.ID = :houseID")
                    .setParameter("houseID",houseID);
            house = (House) query.getResultList().get(0);
            System.out.println(house);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return house;
        }
    }

    public void update(int houseID, String newName){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            House house = getHouse(houseID);
            if(house != null){
                house.setName(newName);
                manager.persist(house);
                transaction.commit();
                System.out.println("Se Actualizo correctamente");
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {

            //manager.close();
        }
        System.out.println( "Complete!" );
    }

    public void delete(int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            House house = getHouse(houseID);
            if(house != null){
                int delete = manager.createQuery("Delete FROM House  h where h.ID = :houseID")
                        .setParameter("houseID", house.getID()).executeUpdate();
                System.out.println(delete);
                transaction.commit();
                System.out.println("Se borro correctamente");
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            //manager.close();
        }
        System.out.println( "Complete!" );
    }

}
