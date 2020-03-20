/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

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
public interface SpecialisationRepository extends JpaRepository<Specialisation, Long>{
    @Query("select d.intitule from Specialisation s, Departement d where s.id = d.id and s.code =:x")
    public String findSpecialisationByDepartement(@Param("x")String specialisation);
    @Query("select s.code from Specialisation s, Parcours p where s.id=p.id and p.nom =:x")
    public String findSpecialisationByParcours(@Param("x")String nom);
    @Query("select s from Specialisation s where s.active = 1")
    public List<Specialisation> findAllSpecialisation();
}
