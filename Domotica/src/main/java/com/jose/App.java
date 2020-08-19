package com.jose;

import com.jose.controller.Controller;
import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.Validation.StringValidation;
import com.jose.model.bootstraper.EMFBootstrapper;
import com.jose.model.crud.*;
import com.jose.model.operations.AreaFunctions;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.operations.UserFunctions;
import com.jose.model.schemas.*;
import com.jose.view.LogRegView.LogHomeView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        EMFBootstrapper.initConnection();
//        Controller con = new Controller();
        System.out.println(UserCRUD.getNumbersOfUserFromHouse(1));
        EMFBootstrapper.closeEntityManager();

    }
}
