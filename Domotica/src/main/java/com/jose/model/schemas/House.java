package com.jose.model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private int ID;
    @Column(name = "house_name")
    private String name;
    @Column(name = "house_code")
    private String code;

    public House(String _name,String _code){
        this.name = _name;
        this.code = _code;
    }

    public House(){}


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
