/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.Parcours;
import com.ensp.agem.data.Specialisation;
import java.util.List;
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
public interface ParcoursRepository extends JpaRepository<Parcours, Long>{
    @Query("select s.code, s.parcours.id from Specialisation s, Parcours p where s.id = p.id and s.parcours.id=p.id and p.nom =:x")
    public String findParoursBySpecialisation(@Param("x")String nom);
    @Query("select p from Parcours p where p.active = 1")
    public List<Parcours> findAllParcours();
}
