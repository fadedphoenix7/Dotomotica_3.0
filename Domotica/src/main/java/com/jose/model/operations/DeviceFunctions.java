package com.jose.model.operations;

import com.jose.Exceptions.DeviceException;
import com.jose.Exceptions.NoExitsException;
import com.jose.Exceptions.UserException;
import com.jose.model.crud.DeviceCRUD;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

import java.util.ArrayList;
import java.util.List;

public class DeviceFunctions {
    public static void addDevice(String name, String deviceDescription, int houseID, ArrayList<User> users, UserRole role){
        Device newDevice = new Device();
        newDevice.setNameDevice(name);
        newDevice.setDescriptionDevice(deviceDescription);
        newDevice.setID_house(houseID);
        newDevice.setUsers(users);
        newDevice.setUserRole(role);

        DeviceCRUD.create(newDevice);
    }

    public static void updateDevice(Device device,String newName, String descriptionDevice, UserRole role, List<User> users){
        try{
            if(device == null){
                throw new NoExitsException(Device.class);
            }
            else{
                reConfigDevice(device, newName, descriptionDevice, role, users);
                DeviceCRUD.update(device);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void reConfigDevice(Device device,String newName,String deviceDescription ,UserRole role, List<User> users){
        if(!newName.isEmpty() ||device.getNameDevice().equals(newName)) device.setNameDevice(newName);
        if(!deviceDescription.isEmpty() ||device.getDescriptionDevice().equals(deviceDescription)) device.setDescriptionDevice(deviceDescription);
        device.setUserRole(role);
        device.setUsers(users);
    }

    public static void deleteDevice(Device device, UserRole userRole){
        try{
            if(device == null){
                throw new NoExitsException(Device.class);
            }
            else{
                if(userRole == UserRole.MODERATOR || userRole == UserRole.ADMIN){
                    DeviceCRUD.delete(device);
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

    public static void turnOnDevice(Device device){
        try{
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

    public static void turnOffDevice(Device device){
        try{
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

    public static void deviceAddUser(int deviceID, int userID){
        try{
            User user = UserCRUD.getUserByID(userID);
            Device device = DeviceCRUD.getDeviceByID(deviceID);
            if(device == null)  throw new NoExitsException(Device.class);
            if(user == null)    throw new NoExitsException(User.class);
            if(exitsRelation( device, user)) throw new DeviceException(1);
            else{
                device.addUser(user);
                user.getDevices().add(device);
                DeviceCRUD.update(device);
            }
        }catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
        catch (DeviceException error) {
            System.out.println(error.getMessage());
        }
    }

    public static ArrayList<User> getUsersInHouse(int houseID, int deviceID){
        return UserCRUD.getUsersInHouseAndNoDevice(houseID, deviceID);
    }

    public static ArrayList<User> getUsersInDevice(int deviceID, int houseID){
        return UserCRUD.getUsersInDevice(deviceID, houseID);
    }

    public static ArrayList<Device> getDevicesFromArea(int areaID){
        return DeviceCRUD.getDeviceFromArea(areaID);
    }

    public static ArrayList<Device> getDevicesFromHouseNotArea(int areaID, int houseID){
        return DeviceCRUD.getDeviceFromHouseNotArea(areaID, houseID);
    }

    public static boolean exitsRelation(Device device, User user){
        return device.getUsers().contains(user);
    }

    public static ArrayList<Device> getDevicesOn(int userID, int houseID, UserRole role){
        return DeviceCRUD.getDevicesOn(userID, houseID, role);
    }

    public static ArrayList<Device> getDeviceFromUser(int userID, int houseID, UserRole role){
        return DeviceCRUD.getDeviceFromUser(userID, houseID, role);
    }

}
