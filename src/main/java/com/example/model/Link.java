package com.example.model;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "link")
@Table(name = "link")
public class Link extends CommonEntity {

    @Column(name = "link")
    private String link;

    @Column(name = "title")
    private String title;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
