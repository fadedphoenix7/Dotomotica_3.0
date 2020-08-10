package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class DeviceCRUD {

    private static EntityManager manager = EMFBootstrapper.openEntityManager();
    private static EntityTransaction transaction = manager.getTransaction();

    public static Device getDevice(int deviceID){
        Device device = null;
        try {
            Query query = manager.createQuery("Select d From Device d WHERE d.ID = : deviceID")
                    .setParameter("deviceID",deviceID);
            device = (Device) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return device;
        }
    }

    public static void create(Device device){
        try {
            transaction.begin();
            manager.persist(device);
            transaction.commit();
            System.out.println("Se Agreo correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void update(Device device){
        try {
            transaction.begin();
            manager.persist(device);
            transaction.commit();
            System.out.println("Se actualizo correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void delete(Device device){
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM Device  d where d.ID = :deviceID")
                    .setParameter("deviceID", device.getID()).executeUpdate();
            System.out.println(delete);
            transaction.commit();
            System.out.println("Se borro correctamente");

        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }
}
