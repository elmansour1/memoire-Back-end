/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.Utilisateur;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mansour
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Optional<Utilisateur> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("select u from Utilisateur u where u.active = 1")
    public List<Utilisateur> findAllUtilisateur();
}
