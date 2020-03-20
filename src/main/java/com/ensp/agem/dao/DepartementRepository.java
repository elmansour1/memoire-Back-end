/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.Departement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author mansour
 */
@SuppressWarnings("unused")
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long>{
    @Query("select d from Departement d where d.active = 1")
    public List<Departement> findAllDepartement();
}
