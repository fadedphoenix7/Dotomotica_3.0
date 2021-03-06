package com.jose.model.schemas;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private int ID;
    @Column(name = "area_name")
    private String nameArea;
    @Column(name = "house_id")
    private int ID_house;
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinTable(name = "AreaToArea",
            joinColumns = @JoinColumn(name = "parent_area_id"),
            inverseJoinColumns = @JoinColumn(name = "child_area_id"))
    private List<Area> areas_child = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinTable(name = "AreaToUser",
            joinColumns = @JoinColumn(name = "parent_area_id"),
            inverseJoinColumns = @JoinColumn(name = "child_user_id"))
    private List<User> users = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch= FetchType.EAGER)
    @JoinTable(name = "AreaToDevice",
            joinColumns = @JoinColumn(name = "parent_area_id"),
            inverseJoinColumns = @JoinColumn(name = "child_device_id"))
    private List<Device> devices = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "AreaToArea",
            joinColumns = @JoinColumn(name = "child_area_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_area_id"))
    private List<Area> areas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "area_role")
    private UserRole userRole = UserRole.USER;

    public Area(){}

    public Area(String _name, int _houseID){
        this.nameArea = _name;
        this.ID_house = _houseID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public int getID_house() {
        return ID_house;
    }

    public void setID_house(int ID_house) {
        this.ID_house = ID_house;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Area> getAreas_child() {
        return areas_child;
    }

    public void setAreas_child(List<Area> areas_child) {
        this.areas_child = areas_child;
    }

    public void addArea(Area area){
        areas.add(area);
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public void addUser(User user){
        users.add(user);
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String toString(){
//        return "ID: " + this.ID + "\n\tNombre: " + this.nameArea
//                + "\n\tID_House: " + this.ID_house +"\n"
//                /*+"\tAreaParent: " + areas.toString() */+ "\n\tDevices: " + devices.toString() +
//                "\n\tUsers: " + users.toString() + "\n\tAreaChilds: " + areas_child.toString();
        return "\nNombre: " + this.nameArea;
    }

    public boolean equals(Area area){
        return this.getID() == area.getID();
    }
}
