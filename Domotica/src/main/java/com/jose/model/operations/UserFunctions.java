package com.jose.model.operations;

import com.jose.Exceptions.ExitsException;
import com.jose.Exceptions.NoExitsException;
import com.jose.Exceptions.UserException;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.UserRole;
import com.jose.model.schemas.User;

public class UserFunctions {

    public static void addUser(String userName, String lastName, String userEmail,
                               String password, int idHouse, UserRole userRole){
        try{
            if(UserCRUD.exitsUser(userEmail)){
                throw new ExitsException(User.class);
            }
            else{
                User newUser = new User();
                newUser.setUserName(userName);
                newUser.setLastName(lastName);
                newUser.setEmail(userEmail);
                newUser.setId_house(idHouse);
                newUser.setPassword(password);
                newUser.setUserRole(userRole);
                UserCRUD.create(newUser);
            }
        }
        catch(ExitsException error){
            System.out.println(error.getMessage());
        }
    }

    public static void updateUser(String userName, String lastName, String userEmail,
                           String password, int idHouse, UserRole userRole){
        try{
            User exitsUser = UserCRUD.getUser(userEmail);
            System.out.println(exitsUser);
            if(exitsUser == null){
                throw new NoExitsException(User.class);
            }
            else{
                reConfigUser(exitsUser, userName, lastName,userEmail, password, idHouse, userRole);
                UserCRUD.update(exitsUser);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void reConfigUser(User user, String userName, String lastName, String userEmail,
                             String password, int idHouse, UserRole userRole){
        if(!user.getUserName().equals(userName) && !userName.isEmpty()) user.setUserName(userName);
        if(!user.getLastName().equals(lastName) && !lastName.isEmpty()) user.setLastName(lastName);
        if(!user.getEmail().equals(userEmail) && !userEmail.isEmpty()) user.setEmail(userEmail);
        if(!user.getPassword().equals(password) && !password.isEmpty()) user.setPassword(password);
        user.setUserRole(userRole);
    }

    public static void deleteUser(String userEmail, String password){
        try{
            User exitsUser = UserCRUD.getUser(userEmail);
            if(exitsUser == null){
                throw new NoExitsException(User.class);
            }
            else{
                if(exitsUser.getPassword().equals(password)){
                    UserCRUD.delete(exitsUser);
                }
                else{
                    throw new UserException(1);
                }
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }catch (UserException error){
            System.out.println(error.getMessage());
        }
    }

}
