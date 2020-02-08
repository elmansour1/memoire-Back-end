/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.Memoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mansour
 */
@SuppressWarnings("unused")
@Repository
public interface MemoireRepository extends JpaRepository<Memoire, Long>{
    public Memoire findMemoireByTitre(String titre);
    public Memoire findMemoireByMotsCles(String motsCle);
    public Memoire findMemoireByResume(String resume);
    public Memoire findMemoireByAbstrat(String asbtrat);

}
