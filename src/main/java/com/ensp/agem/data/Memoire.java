/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@Entity
public class Memoire implements Serializable{
    @Id    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "int default 1")
    private int active;
    private String titre;

    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @Temporal(TemporalType.DATE)
    private Date anneesSoutenance;
    private String motsCles;
    private String resume;
    private String abstrat;

    private String document;
    
    
//    private Specialisation specialisation;
    
    @ManyToMany
    @JoinTable
    private List<Enseignant> encadreurs;

    @ManyToMany(cascade = {
        CascadeType.DETACH 
    })
    @JoinTable
    private List<Auteur> auteurs;

    public Memoire() {
    }

    public Memoire(String titre, Date datePublication, Date anneesSoutenance, String motsCles, String resume, String abstrat, String document, List<Enseignant> encadreurs, List<Auteur> auteurs) {
        this.titre = titre;
        this.datePublication = datePublication;
        this.anneesSoutenance = anneesSoutenance;
        this.motsCles = motsCles;
        this.resume = resume;
        this.abstrat = abstrat;
        this.document = document;
        this.encadreurs = encadreurs;
        this.auteurs = auteurs;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Date getAnneesSoutenance() {
        return anneesSoutenance;
    }

    public void setAnneesSoutenance(Date anneesSoutenance) {
        this.anneesSoutenance = anneesSoutenance;
    }

    public String getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getAbstrat() {
        return abstrat;
    }

    public void setAbstrat(String abstrat) {
        this.abstrat = abstrat;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Enseignant> getEncadreurs() {
        return encadreurs;
    }

//    public Specialisation getSpecialisation() {
//        return specialisation;
//    }
//
//    public void setSpecialisation(Specialisation specialisation) {
//        this.specialisation = specialisation;
//    }

    
    public void setEncadreurs(List<Enseignant> encadreurs) {
        this.encadreurs = encadreurs;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    @Override
    public String toString() {
        return "Memoire{" + "id=" + id + ", titre=" + titre + ", datePublication=" + datePublication + ", motsCles=" + motsCles + ", resume=" + resume + ", abstrat=" + abstrat + ", document=" + document + ", encadreurs=" + encadreurs + ", auteurs=" + auteurs + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.titre);
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
        final Memoire other = (Memoire) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }
    
    
    
}
