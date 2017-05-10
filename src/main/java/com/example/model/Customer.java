package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Entity(name = "customer")
@Table(name = "customer")
public class Customer extends CommonEntity {

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
