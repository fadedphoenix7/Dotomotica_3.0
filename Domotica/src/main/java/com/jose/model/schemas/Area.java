package com.jose.model.schemas;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Area")
public class Area {
    @Id
    private int ID;
    @Column(name = "area_name")
    private String nameArea;
    @Column(name = "house_id")
    private int ID_house;
    @ManyToMany
    private Set<Area> areas;
    @ManyToMany
    private Set<User> users;
    @ManyToMany
    private Set<Device> devices;

    public Area(){}

    public Area(int _ID, String _name, int _houseID){
        this.ID  = _ID;
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

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}
