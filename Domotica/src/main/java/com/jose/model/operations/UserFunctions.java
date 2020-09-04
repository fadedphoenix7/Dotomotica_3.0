package com.jose.model.operations;

import com.jose.Exceptions.ExitsException;
import com.jose.Exceptions.NoExitsException;
import com.jose.Exceptions.UserException;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.UserRole;
import com.jose.model.schemas.User;

import java.util.ArrayList;

public class UserFunctions {

    public static void addUser(String userName, String lastName, String userEmail,
                               String password, int idHouse, UserRole userRole){
        try{
            if(UserCRUD.exitsUserByEmailAndHouseID(userEmail,idHouse)){
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

    public static void updateUser(User user){
        try{
            if(user == null){
                throw new NoExitsException(User.class);
            }
            else{
                UserCRUD.update(user);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void deleteUser(String userEmail, String password,int houseID){
        try{
            User exitsUser = UserCRUD.getUserByEmail(userEmail, houseID);
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

    public static void deleteUser(User user,int houseID){
        try{
            if(user == null){
                throw new NoExitsException(User.class);
            }
            else{
                UserCRUD.delete(user);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static ArrayList<User> getUsersFromHouse(int userID, int houseID, UserRole role){
        return UserCRUD.getUsersInHouseRole(userID, houseID, role);
    }

}
