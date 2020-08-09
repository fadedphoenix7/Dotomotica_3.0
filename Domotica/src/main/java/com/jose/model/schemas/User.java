package com.jose.model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "house_id")
    private int id_house;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole = UserRole.USER;

    public User(String _name, String _lastName, String _email, String _password, int _idHouse, UserRole _role){
        this.userName = _name;
        this.lastName = _lastName;
        this.email = _email;
        this.password = _password;
        this.id_house = _idHouse;
        this.userRole = _role;
    }

    public User(){}


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

    public int getId_house() {
        return id_house;
    }

    public void setId_house(int id_house) {
        this.id_house = id_house;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String toString(){
        return "ID: " + this.ID + "\n\tNombre: " + this.userName + "\n\tApellido: " + this.lastName
                + "\n\tEmail: " + this.email + "\n\t idHouse: " + this.id_house + "\n\tUserRole: " + this.userRole.toString();
    }

}
