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
import javax.persistence.Version;

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@Entity
public class Departement implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "int default 1")
    private int active;
    @Column(nullable = false, unique = true)
    private String code;
    private String intitule;
    
//    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Specialisation> specialisations;

    public Departement() {
    }
    
//      public Departement(String code, String intitule) {
//        this.code = code;
//        this.intitule = intitule;
//    }

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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

//    public List<Specialisation> getSpecialisations() {
//        return specialisations;
//    }
//
//    public void setSpecialisations(List<Specialisation> specialisations) {
//        this.specialisations = specialisations;
//    }

    @Override
    public String toString() {
        return "Departement{" + "code=" + code + ", intitule=" + intitule + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + Objects.hashCode(this.intitule);
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
        final Departement other = (Departement) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        return true;
    }
      
    
}
