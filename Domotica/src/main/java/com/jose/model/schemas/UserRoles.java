package com.jose.model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "userrole")
public class UserRoles{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int ID;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRoles(){}

    public UserRoles(UserRole _role){
        this.role = _role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

