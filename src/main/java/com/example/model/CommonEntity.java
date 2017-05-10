package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@MappedSuperclass
public class CommonEntity implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6893399491928930624L;

    /**
     * Identifiant technique de l'objet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Champ version.
     */
    @Version
    @Column(name = "version" )
    private Integer version= 0;

    /**
     * Constante pour la taille max des champs string.
     */
    public static final int SIZE_STR_MAX = 255;

    /**
     * Constante représentant le pattern de date.
     */
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    /**
     * Getter pour id.
     * @return Le id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter pour id.
     * @param id Le id à écrire.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Getter pour version.
     * @return Le version
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Setter pour version.
     * @param version Le version à écrire.
     */
    public void setVersion(final Integer version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName()).append(" #").append(this.getId()).toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonEntity)) return false;

        CommonEntity that = (CommonEntity) o;

        return (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) ;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        return result;
    }

}
