package com.example.dto;

import com.example.enumeration.ItemEnum;
import com.example.enumeration.ItemPositionEnum;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

public class ItemDTO {

    private Long id;
    private String name;
    private ItemEnum type;
    private Long price;
    private Long requiredLevel;
    private String url;
    private Long duration = 1L;
    private String description;
    private Boolean isReusable;
    private String image;
    private String keyItem;
    private ItemPositionEnum position;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Boolean getIsReusable() {
		return isReusable;
	}

	public void setIsReusable(Boolean isReusable) {
		this.isReusable = isReusable;
	}

	public String getKeyItem() {
		return keyItem;
	}

	public void setKeyItem(String keyItem) {
		this.keyItem = keyItem;
	}

	public ItemPositionEnum getPosition() {
		return position;
	}

	public void setPosition(ItemPositionEnum position) {
		this.position = position;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}
}
