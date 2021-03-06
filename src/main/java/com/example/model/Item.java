package com.example.model;

import com.example.enumeration.ItemEnum;
import com.example.enumeration.ItemPositionEnum;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Entity(name = "item")
@Table(name = "item")
public class Item extends CommonEntity {

    /**
     * Nom de l'objet
     */
    @Column(name = "name")
    private String name;

    /**
     * Type de l'objet
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemEnum type;

    /**
     * Prix
     */
    @Column(name = "price")
    private Long price = 0L;

    /**
     * Niveau requis
     */
    @Column(name = "requiredLevel")
    private Long requiredLevel = 1L;

    /**
     * Image à afficher
     */
    @Column(name = "image")
    private String image;

    /**
     * URL de l'image (pour les avatars et les fonds d'écran)
     */
    @Column(name = "url")
    private String url;

    /**
     * Description
     */
    @Column(name = "description")
    private String description;

    /**
     * est ré-utilisable (disparaît ou non de l'inventaire après utilisation)
     */
    @Column(name = "is_reusable")
    private Boolean isReusable = Boolean.FALSE;
    
    /**
     * Durée avant expiration
     */
    @Column(name = "duration")
    private Long duration;
    
    /**
     * Clé item
     */
    @Column(name = "key_item", unique=true)
    private String keyItem;
    
    /**
     * Position image
     */
    @Column(name = "position")
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

	public Boolean getIsReusable() {
		return isReusable;
	}

	public void setIsReusable(Boolean isReusable) {
		this.isReusable = isReusable;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
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
	
}
