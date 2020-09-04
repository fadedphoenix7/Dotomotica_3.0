package com.jose.model.schemas;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "device_description")
    private String descriptionDevice;
    @Column(name = "house_id")
    private int ID_house;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinTable(name = "DeviceToUser",
                joinColumns = @JoinColumn(name = "parent_device_id"),
                inverseJoinColumns = @JoinColumn(name = "child_user_id"))
    private List<User> users = new ArrayList<>();
    @Column(name = "device_state")
    private boolean state;
    @Enumerated(EnumType.STRING)
    @Column(name = "device_role")
    private UserRole userRole = UserRole.USER;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "devices")
    private List<Area> areas;

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

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean isState() {
        return state;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getDescriptionDevice() {
        return descriptionDevice;
    }

    public void setDescriptionDevice(String descriptionDevice) {
        this.descriptionDevice = descriptionDevice;
    }

    public String toString(){
        if(users == null) return "Usuarios vacios";
        return "ID:" + this.ID +"\n\tNombre: " + this.nameDevice +
                "\n\tState: " + this.state + "\n\tID_house: " + this.ID_house +"\n\t" + users.toString() + "\n";
    }
}
