package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "tag")
@Table(name = "tag")
public class Tag extends CommonEntity {

    /**
     * Code
     */
    @Column(name = "code")
    private String code;

    /**
     * Nom
     */
    @Column(name = "name")
    private String name;

    /**
     * Couleur
     */
    @Column(name = "color")
    private String color;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
