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
    public void create(){
        String code = HouseRegistrationCode.generate(20); //moved of place -->

        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        ArrayList<HouseCode> houseCodes = new ArrayList<HouseCode>();
        try {
            transaction.begin();
            if(!exitsCode(code,manager)){
                HouseCode houseCode = new HouseCode();
                houseCode.setRegistrationCode(code);
                manager.persist(houseCode);
                transaction.commit();
                System.out.println("Si se pudo");
            }
        } catch (PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
        System.out.println( "Complete!" );
    }

    public boolean exitsCode(String code, EntityManager manager){
        EntityTransaction transaction = manager.getTransaction();
        ArrayList<HouseCode> houseCodes = new ArrayList<HouseCode>();
        try {
            Query query = manager.createQuery("Select h From HouseCode h WHERE h.registrationCode = :code")
                    .setParameter("code",code);
            houseCodes = (ArrayList<HouseCode>) query.getResultList();
            System.out.println(houseCodes);

        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            return !houseCodes.isEmpty();
        }
    }
}
