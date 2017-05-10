package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "role")
@Table(name = "role")
public class Role extends CommonEntity {

    @Column(name = "code")
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
