package com.jose.model.crud;

import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;

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

    public static ArrayList<Area> getAreaManage(int userID, int houseID, UserRole Role){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<Area> areas = null;
        try {
            if(Role.equals(UserRole.USER)){

                Query query = manager.createQuery("Select area From Area area Left Join area.users au " +
                        "Where area.ID_house =: houseID and (area.userRole <=: role or au.ID =: userID)")
                        .setParameter("houseID",houseID)
                        .setParameter("role", Role)
                        .setParameter("userID", userID);
                areas= (ArrayList<Area>) query.getResultList();
            }
            else{
                Query query = manager.createQuery("Select area From Area area Left Join area.users au " +
                        "Where area.ID_house =: houseID")
                        .setParameter("houseID",houseID);
                areas= (ArrayList<Area>) query.getResultList();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return areas;
        }
    }

    public static ArrayList<Area> getAreaaFromArea(int areaID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<Area> areas = null;
        try {
            Query query = manager.createQuery("Select area From Area area JOIN area.areas ac where ac.ID = :areaID")
                    .setParameter("areaID", areaID);

            areas= (ArrayList<Area>) query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return areas;
        }
    }

    public static ArrayList<Area> getAreaaFromHouseNotArea(int areaID, int houseID){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<Area> areas = null;
        try {
            Query query = manager.createQuery("Select area From Area area Where area.ID_house = :houseID")
                    .setParameter("houseID", houseID);

            areas= (ArrayList<Area>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            manager.close();
            return areas;
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
            manager.merge(area);
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
