package com.jose.model.operations;

import com.jose.Exceptions.AreaException;
import com.jose.Exceptions.NoExitsException;
import com.jose.Exceptions.UserException;
import com.jose.model.crud.AreaCRUD;
import com.jose.model.crud.DeviceCRUD;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

import java.util.ArrayList;

public class AreaFunctions {
    public static void addArea(String name, int houseID){
        Area newArea = new Area();
        newArea.setNameArea(name);
        newArea.setID_house(houseID);

        AreaCRUD.create(newArea);
    }

    public static void updateArea(int areaID,String newName){
        try{
            Area exitsArea = AreaCRUD.getArea(areaID);
            System.out.println(exitsArea);
            if(exitsArea == null){
                throw new NoExitsException(Area.class);
            }
            else{
                reConfigDevice(exitsArea, newName);
                AreaCRUD.update(exitsArea);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void reConfigDevice(Area area,String newName){
        if(!newName.isEmpty() ||area.getNameArea().equals(newName)) area.setNameArea(newName);
    }

    public static void deleteArea(int areaID, UserRole userRole){
        try{
            Area area = AreaCRUD.getArea(areaID);
            if(area == null){
                throw new NoExitsException(Area.class);
            }
            else{
                if(userRole == UserRole.MODERATOR || userRole == UserRole.ADMIN){
                    AreaCRUD.delete(area);
                }
                else{
                    throw new UserException(2);
                }
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }catch (UserException error){
            System.out.println(error.getMessage());
        }
    }

    public static void turnOnDevices(int deviceID){
        try{
            Device device = DeviceCRUD.getDevice(deviceID);
            if(device == null){
                throw new NoExitsException(User.class);
            }
            else{
                device.setState(true);
                DeviceCRUD.update(device);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void turnOffDevice(int deviceID){
        try{
            Device device = DeviceCRUD.getDevice(deviceID);
            if(device == null){
                throw new NoExitsException(Device.class);
            }
            else{
                device.setState(false);
                DeviceCRUD.update(device);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void areaAddArea(int areaID_parent, int areaID_child){
        try{
            Area parent_area = AreaCRUD.getArea(areaID_parent);
            Area children_area = AreaCRUD.getArea(areaID_child);
            if(parent_area == null)  throw new NoExitsException(Area.class);
            if(children_area == null)    throw new NoExitsException(Area.class);
            if(exitsRelationAA( parent_area, children_area)) throw new AreaException(1);
            else{
                parent_area.addArea(children_area);
                children_area.getAreas_child().add(parent_area);
                AreaCRUD.update(parent_area);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void areaAdDevice(int areaID, int deviceID){
        try{
            Area area = AreaCRUD.getArea(areaID);
            Device device = DeviceCRUD.getDevice(deviceID);
            if(area == null)  throw new NoExitsException(Area.class);
            if(device == null)    throw new NoExitsException(Device.class);
            if(exitsRelationAD( area, device)) throw new AreaException(1);
            else{
                area.addDevice(device);
                device.getAreas().add(area);
                AreaCRUD.update(area);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void areaAddUser(int areaID, int userID){
        try{
            Area area = AreaCRUD.getArea(areaID);
            User user = UserCRUD.getUser(userID);
            if(area == null)  throw new NoExitsException(Area.class);
            if(user == null)    throw new NoExitsException(User.class);
            if(exitsRelationAU( area, user)) throw new AreaException(1);
            else{
                area.addUser(user);
                user.getAreas().add(area);
                AreaCRUD.update(area);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static boolean exitsRelationAA(Area Parea, Area Carea){
        return Parea.getAreas().contains(Carea);
    }

    public static boolean exitsRelationAD(Area Parea, Device Cdevice){
        return Parea.getDevices().contains(Cdevice);
    }

    public static boolean exitsRelationAU(Area Parea, User Cuser){
        return Parea.getUsers().contains(Cuser);
    }
}
