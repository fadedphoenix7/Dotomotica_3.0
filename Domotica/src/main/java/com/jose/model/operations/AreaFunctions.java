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
    public static void addArea(String name,UserRole role, int houseID){
        Area newArea = new Area();
        newArea.setNameArea(name);
        newArea.setUserRole(role);
        newArea.setID_house(houseID);

        AreaCRUD.create(newArea);
    }

    public static void updateArea(Area area){
        try{
            if(area == null){
                throw new NoExitsException(Area.class);
            }
            else{
                AreaCRUD.update(area);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }


    public static void deleteArea(int areaID, UserRole userRole){
        try{
            Area area = AreaCRUD.getAreaByID(areaID);
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
            Device device = DeviceCRUD.getDeviceByID(deviceID);
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
            Device device = DeviceCRUD.getDeviceByID(deviceID);
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

    public static void areaAddArea(Area parentArea, Area childArea){
        try{ ;
            if(parentArea == null)  throw new NoExitsException(Area.class);
            if(childArea == null)    throw new NoExitsException(Area.class);
            if(exitsRelationAA( parentArea, childArea)) throw new AreaException(1);
            else{
                parentArea.getAreas_child().add(childArea);
                AreaCRUD.update(parentArea);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void areaAdDevice(Area area, Device device){
        try{
            if(area == null)  throw new NoExitsException(Area.class);
            if(device == null)    throw new NoExitsException(Device.class);
            if(exitsRelationAD( area, device)) throw new AreaException(1);
            else{
                area.addDevice(device);
                AreaCRUD.update(area);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void removeAreaToArea(Area parentArea, Area childArea){
        try{ ;
            if(parentArea == null)  throw new NoExitsException(Area.class);
            if(childArea == null)    throw new NoExitsException(Area.class);
            if(!exitsRelationAA( parentArea, childArea)) throw new AreaException(1);
            else{
                parentArea.getAreas_child().remove(childArea);
                AreaCRUD.update(parentArea);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (AreaException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void removeDeviceToArea(Area area, Device device){
        try{
            if(area == null)  throw new NoExitsException(Area.class);
            if(device == null)    throw new NoExitsException(Device.class);
            if(!exitsRelationAD( area, device)) throw new AreaException(1);
            else{
                area.getDevices().remove(device);
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
            Area area = AreaCRUD.getAreaByID(areaID);
            User user = UserCRUD.getUserByID(userID);
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
        return Parea.getAreas_child().contains(Carea);
    }

    public static boolean exitsRelationAD(Area Parea, Device Cdevice){
        return Parea.getDevices().contains(Cdevice);
    }

    public static boolean exitsRelationAU(Area Parea, User Cuser){
        return Parea.getUsers().contains(Cuser);
    }

    public static ArrayList<Area> getAreasFromHouse(int userID, int houseID, UserRole userRole){
        return AreaCRUD.getAreaManage(userID, houseID, userRole);
    }

    public static ArrayList<Area> getAreasFromArea(int areaID){
        return AreaCRUD.getAreaaFromArea(areaID);
    }

    public static ArrayList<Area> getAreaFromHouseNotArea(int areaID, int houseID){
        ArrayList<Area> homeAreas = AreaCRUD.getAreaaFromHouseNotArea(areaID, houseID);
        ArrayList<Area> areasFromArea = getAreasFromArea(areaID);
        ArrayList<Area> finalAreas = new ArrayList<>();

        homeAreas.forEach(area -> {
            areasFromArea.forEach(areasChild -> {
                if(area.equals(areasChild)) finalAreas.add(area);
            });
            if(area.getID() == areaID) finalAreas.add(area);
        });

        homeAreas.removeAll(finalAreas);

        return homeAreas;
    }

    public static ArrayList<Area> getAreasManage(int userID, int houseID, UserRole role){
        return AreaCRUD.getAreaManage(userID, houseID, role);
    }

    public static ArrayList<Integer> passedArea;

    public static ArrayList<Integer> getPassedArea() {
        return passedArea;
    }

    public static void setPassedArea(ArrayList<Integer> passedArea) {
        AreaFunctions.passedArea = passedArea;
    }

    public static void turnOnArea(Area area){
        passedArea.add(area.getID());
        area.getDevices().forEach(device -> DeviceFunctions.turnOnDevice(device));
        area.getAreas_child().forEach(childArea -> {
            if(!passedArea.contains(childArea.getID())) turnOnArea(childArea);
        });
    }

    public static void turnOffArea(Area area){
        passedArea.add(area.getID());
        area.getDevices().forEach(device -> DeviceFunctions.turnOffDevice(device));
        area.getAreas_child().forEach(childArea -> {
            if(!passedArea.contains(childArea.getID())) turnOffArea(childArea);
        });
    }
}
