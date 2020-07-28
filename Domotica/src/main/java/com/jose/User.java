package com.jose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @Column(name = "user_id")
    private int ID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_lastname")
    private String lastName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;

    public User(int _id, String _name, String _lastname, String _email, String _password){
        this.ID = _id;
        this.userName = _name;
        this.lastName = _lastname;
        this.email = _email;
        this.password = _password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
