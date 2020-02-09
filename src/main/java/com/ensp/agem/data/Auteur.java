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
public class Auteur implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "int default 1")
    private int active;
    @Column(nullable = false)
    private String matricule;
    private String nom;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telephone;
    private String motDePasse;
    
    public Auteur(){
        
    }

    public Auteur(String matricule, String nom, String email, String telephone, String motDePasse) {
        this.matricule = matricule;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Auteur{" + "id=" + id + ", active=" + active + ", matricule=" + matricule + ", nom=" + nom + ", email=" + email + ", telephone=" + telephone + ", motDePasse=" + motDePasse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.matricule);
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.telephone);
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
        final Auteur other = (Auteur) obj;
        if (!Objects.equals(this.matricule, other.matricule)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        return true;
    }
    
    
}
