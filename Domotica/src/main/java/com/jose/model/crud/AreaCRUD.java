package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.User;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class AreaCRUD {

    public static Area getAreaByID(int areaID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Area area = null;
        try {
            Query query = manager.createQuery("Select a From Area a WHERE a.ID = : AreaID")
                    .setParameter("AreaID",areaID);
            area = (Area) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return area;
        }
    }

    public static void create(Area area){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(area);
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

    public static void update(Area area){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(area);
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

    public static void delete(Area area){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM Area  a where a.ID = :AreaID")
                    .setParameter("AreaID", area.getID()).executeUpdate();
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
