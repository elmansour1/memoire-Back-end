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

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"specialisation_id"}))
public class Parcours implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "int default 1")
    private int active;
    private String nom;
    

    public Parcours() {
    }
    
    public Parcours(String nom) {
        this.nom = nom;
//        this.specialisation = specialisation;
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
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//    public List<Specialisation> getSpecialisation() {
//        return specialisation;
//    }
//
//    public void setSpecialisation(List<Specialisation> specialisation) {
//        this.specialisation = specialisation;
//    }
//    

    @Override
    public String toString() {
        return "Parcours{" + "nom=" + nom + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nom);
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
        final Parcours other = (Parcours) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
   

   
    
    
    
    
}
