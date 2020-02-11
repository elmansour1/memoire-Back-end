/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.security.services;

import com.ensp.agem.dao.UtilisateurRepository;
import com.ensp.agem.data.Utilisateur;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mansour
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UtilisateurRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Utilisateur  user;
        try {
            user = (Utilisateur) userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            return UserDetailsImpl.build(user);
        } catch (Throwable ex) {
            Logger.getLogger(UserDetailsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
		    }
    
}
