/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.dao;

import com.ensp.agem.data.ERole;
import com.ensp.agem.data.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mansour
 */
@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
    @Query("select r from Role r where r.active = 1")
    public List<Role> findAllRole();
}
