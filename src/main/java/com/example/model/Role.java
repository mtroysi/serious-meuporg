package com.example.model;

import com.example.enumeration.RoleEnum;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "role")
@Table(name = "role")
public class Role extends CommonEntity {

    /**
     * Role.
     */
    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private RoleEnum code;

    public RoleEnum getCode() {
        return code;
    }

    public void setCode(RoleEnum code) {
        this.code = code;
    }
}
