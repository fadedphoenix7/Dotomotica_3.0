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
    @Column(name = "house_registration_id")
    private int registrationCodeID;

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

    public int getRegistrationCodeID() {
        return registrationCodeID;
    }

    public void setRegistrationCodeID(int registrationCodeID) {
        this.registrationCodeID = registrationCodeID;
    }

    public String toString(){
        return "ID:" + this.ID + " \n\tName: " + this.name + " \n\tCode: " + this.code + " \n\tRegisterIDCode: " + this.registrationCodeID;
    }
}
