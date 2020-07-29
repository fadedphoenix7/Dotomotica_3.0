package com.jose.model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "House")
public class House {
    @Id
    @Column(name = "house_id")
    private int ID;
    @Column(name = "house_name")
    private String name;
    @Column(name = "house_code")
    private String code;

    public House(int _id, String _name,String _code){
        this.ID = _id;
        this.name = _name;
        this.code = _code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
