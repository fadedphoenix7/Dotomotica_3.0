package com.jose.model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "HouseCode")
public class HouseCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_code_id")
    private int ID;
    @Column(name = "house_registration_code")
    private String registrationCode;

    public HouseCode(){}

    public HouseCode(String _code){
        this.registrationCode = _code;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
