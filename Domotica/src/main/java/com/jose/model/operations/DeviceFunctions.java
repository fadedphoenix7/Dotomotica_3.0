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

public class DeviceFunctions {
    public static void addDevice(String name, int houseID, ArrayList<User> users, boolean usersCanUse){
        Device newDevice = new Device();
        newDevice.setNameDevice(name);
        newDevice.setID_house(houseID);
        newDevice.setUsers(users);
        newDevice.setUsersCanUse(usersCanUse);

        DeviceCRUD.create(newDevice);
    }

    public static void updateDevice(int deviceID,String newName, boolean usersCanUse){
        try{
            Device exitsDevice = DeviceCRUD.getDeviceByID(deviceID);
            System.out.println(exitsDevice);
            if(exitsDevice == null){
                throw new NoExitsException(Device.class);
            }
            else{
                reConfigDevice(exitsDevice, newName, usersCanUse);
                DeviceCRUD.update(exitsDevice);
            }
        } catch (NoExitsException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void reConfigDevice(Device device,String newName, boolean usersCanUse){
        if(!newName.isEmpty() ||device.getNameDevice().equals(newName)) device.setNameDevice(newName);
        device.setUsersCanUse(usersCanUse);
    }

    public static void deleteDevice(int deviceID, UserRole userRole){
        try{
            Device exitsDevice = DeviceCRUD.getDeviceByID(deviceID);
            if(exitsDevice == null){
                throw new NoExitsException(Device.class);
            }
            else{
                if(userRole == UserRole.MODERATOR || userRole == UserRole.ADMIN){
                    DeviceCRUD.delete(exitsDevice);
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

    public static void turnOnDevice(int deviceID){
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

    public static boolean exitsRelation(Device device, User user){
        return device.getUsers().contains(user);
    }

}
