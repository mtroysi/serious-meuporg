package com.example.model;

import com.example.enumeration.ItemEnum;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Entity(name = "item")
@Table(name = "item")
public class Item extends CommonEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemEnum type;

    @Column(name = "price")
    private Long price = 0L;

    @Column(name = "requiredLevel")
    private Long requiredLevel = 1L;

    @Column(name = "image")
    private String image;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "is_reusable")
    private Boolean isReusable = Boolean.FALSE;

    @Column(name = "duration")
    private Integer duration;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemEnum getType() {
        return type;
    }

    public void setType(ItemEnum type) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public Boolean getIsReusable() {
		return isReusable;
	}

	public void setIsReusable(Boolean isReusable) {
		this.isReusable = isReusable;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
