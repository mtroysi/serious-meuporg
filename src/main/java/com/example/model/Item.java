package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Entity(name = "item")
@Table(name = "item")
public class Item extends CommonEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Long price = 0L;

    @Column(name = "requiredLevel")
    private Long requiredLevel = 1L;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "is_reusable")
    private Boolean isReusable = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Long requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getReusable() {
        return isReusable;
    }

    public void setReusable(Boolean reusable) {
        isReusable = reusable;
    }
}
