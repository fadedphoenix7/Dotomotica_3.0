package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class AreaCRUD {

    private static EntityManager manager = EMFBootstrapper.openEntityManager();
    private static EntityTransaction transaction = manager.getTransaction();

    public static Area getArea(int areaID){
        Area area = null;
        try {
            Query query = manager.createQuery("Select a From Area a WHERE a.ID = : AreaID")
                    .setParameter("AreaID",areaID);
            area = (Area) query.getResultList().get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return area;
        }
    }

    public static void create(Area area){
        try {
            transaction.begin();
            manager.persist(area);
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

    public static void update(Area area){
        try {
            transaction.begin();
            manager.persist(area);
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

    public static void delete(Area area){
        try {
            transaction.begin();
            int delete = manager.createQuery("Delete FROM Area  a where a.ID = :AreaID")
                    .setParameter("AreaID", area.getID()).executeUpdate();
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
