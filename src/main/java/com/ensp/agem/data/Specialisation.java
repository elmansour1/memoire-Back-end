/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.data;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@Entity
//@NamedQueries({
//    @NamedQuery(name = "Specialisation.findDepartement",query = "select d from Departement d where d.id = :idParam")
//})
public class Specialisation implements Serializable{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "int default 1")
    private int active;
    private String code;
    private String description;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Departement departement;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Parcours parcours;

    public Specialisation() {
    }

    public Specialisation(String code, String description, Departement departement, Parcours parcours) {
        this.code = code;
        this.description = description;
        this.departement = departement;
        this.parcours = parcours;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }

    @Override
    public String toString() {
        return "Specialisation{" + "code=" + code + ", description=" + description + ", departement=" + departement + ", parcours=" + parcours + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Specialisation other = (Specialisation) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

 
    
}
