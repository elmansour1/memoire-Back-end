/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.Auteur;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mansour
 */
@SuppressWarnings("unused")
@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long>{
    public Auteur findAuteurByNom(String auteur);
    public Auteur findAuteurByEmail(String auteur);
    public Auteur findAuteurByTelephone(String auteur);
    @Query("select a from Auteur a where a.active = 1")
    public List<Auteur> findAllAuteur();
}
