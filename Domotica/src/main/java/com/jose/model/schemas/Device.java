package com.jose.model.schemas;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private int ID;
    @Column(name = "device_name")
    private String nameDevice;
    @Column(name = "house_id")
    private int ID_house;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "DeviceToUser",
                joinColumns = @JoinColumn(name = "parent_device_id"),
                inverseJoinColumns = @JoinColumn(name = "child_user_id"))
    private List<User> users;

    public Device(){}

    public Device(String _name, int _id_house, List<User> _users){
        this.nameDevice = _name;
        this.ID_house = _id_house;
        this.users = _users;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public int getID_house() {
        return ID_house;
    }

    public void setID_house(int ID_house) {
        this.ID_house = ID_house;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
