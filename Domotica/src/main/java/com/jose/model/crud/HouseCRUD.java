package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.House;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class HouseCRUD {
    public void create(String name, String code, String registerCode){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            int registrationCodeID = exitsHouse(registerCode,manager);
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

    public int exitsHouse(String registerCode,EntityManager manager){
        EntityTransaction transaction = manager.getTransaction();
        int registerCdoeID = -1;
        try {
            Query query = manager.createQuery("Select h.ID From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",registerCode);
            System.out.println(query.getResultList());
            registerCdoeID = (int) query.getResultList().get(0);
            System.out.println(registerCdoeID);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return registerCdoeID;
        }
    }

    public House getHouse(int houseID, EntityManager manager){
        EntityTransaction transaction = manager.getTransaction();
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
            House house = getHouse(houseID, manager);
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
            House house = getHouse(houseID, manager);
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
