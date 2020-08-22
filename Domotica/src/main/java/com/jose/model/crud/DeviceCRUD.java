package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;

import javax.persistence.*;

public class DeviceCRUD {

    public static Device getDeviceByID(int deviceID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Device device = null;
        try {
            Query query = manager.createQuery("Select d From Device d WHERE d.ID = : deviceID")
                    .setParameter("deviceID",deviceID);
            device = (Device) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            manager.close();
            return device;
        }
    }

   /* public static boolean exitsRelation(int deviceID, int userID){
        boolean containUser = false;
        Device device  = null;
        try {
            System.out.println("aaaaa");
            Query query = manager.createQuery("Select rdu From Device rdu Join rdu.users ru " +
                    " where rdu.ID = :deviceID and ru.ID =: userID")
                    .setParameter("userID",userID)
                    .setParameter("deviceID", deviceID);
            System.out.println("aaa");
            System.out.println(query.getResultList());
            device = (Device) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return containUser;
        }

    }*/

    public static void create(Device device){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(device);
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

    public static void update(Device device){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(device);
            transaction.commit();
            System.out.println("Se actualizo correctamente");

        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static void delete(Device device){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM Device  d where d.ID = :deviceID")
                    .setParameter("deviceID", device.getID()).executeUpdate();
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
