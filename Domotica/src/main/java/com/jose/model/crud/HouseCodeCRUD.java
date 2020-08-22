package com.jose.model.crud;

import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.schemas.HouseCode;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;

public class HouseCodeCRUD {

    public static void create(String code){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            HouseCode houseCode = new HouseCode();
            houseCode.setRegistrationCode(code);
            manager.persist(houseCode);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public static boolean exitsCode(String code){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<HouseCode> houseCodes = new ArrayList<HouseCode>();

        try {
            Query query = manager.createQuery("Select h From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",code);
            houseCodes = (ArrayList<HouseCode>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return !houseCodes.isEmpty();
        }
    }

    public static HouseCode getCodeByCode(String code){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        ArrayList<HouseCode> houseCodes = new ArrayList<HouseCode>();

        try {
            Query query = manager.createQuery("Select h From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",code);
            houseCodes = (ArrayList<HouseCode>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return houseCodes.get(0);
        }
    }
}
