/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.UtilisateurRepository;
import com.ensp.agem.data.Role;
import com.ensp.agem.data.Utilisateur;
import com.ensp.agem.errors.BadRequestAlertException;
import com.ensp.agem.payload.response.MessageResponse;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mansour
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UtilisateurRestController {
    private final Logger log = LoggerFactory.getLogger(ParcoursRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_utilisateur";
    
    @Inject
    private UtilisateurRepository utilisateurRepository;
    
    public UtilisateurRestController(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
    
     /**
     * {@code POST  /utilisateurs} : Create a new utilisateur.
     *
     * @param utilisateur the utilisateur to create.
     * @param roles
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new utilisateur, or with status {@code 400 (Bad Request)} if the utilisateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/utilisateurs")
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur utilisateur) throws URISyntaxException {
        log.debug("REST request to save Utilisateur : {}", utilisateur);
        if (utilisateur.getId() != null) {
            throw new BadRequestAlertException("A new utilisateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
                    return ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Username is already taken!"));
            }

            if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
                    return ResponseEntity
                                    .badRequest()
                                    .body(new MessageResponse("Error: Email is already in use!"));
            }
        utilisateur.setActive(1);
//        utilisateur.setRoles(roles);
        utilisateurRepository.save(utilisateur);
     //   utilisateurSearchRepository.save(result);
//        return ResponseEntity.created(new URI("/api/utilisateurs/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert("Utilisateur", false, ENTITY_NAME, result.getId().toString()))
//            .body(result);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /**
     * {@code PUT  /utilisateurs} : Updates an existing utilisateur.
     *
     * @param utilisateur the utilisateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated utilisateur,
     * or with status {@code 400 (Bad Request)} if the utilisateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the utilisateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur) throws URISyntaxException {
        log.debug("REST request to update Utilisateur : {}", utilisateur);
        if (utilisateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Utilisateur result = utilisateurRepository.save(utilisateur);
//        utilisateurSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Utilisateur", false, ENTITY_NAME, utilisateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /utilisateurs} : get all the utilisateurs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of utilisateurs in body.
     */
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAllUtilisateurs() {
        log.debug("REST request to get all Utilisateurs");
        return utilisateurRepository.findAll();
    }

    /**
     * {@code GET  /utilisateurs/:id} : get the "id" utilisateur.
     *
     * @param id the id of the utilisateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the utilisateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        log.debug("REST request to get Utilisateur : {}", id);
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(utilisateur);
    }

    /**
     * {@code DELETE  /utilisateurs/:id} : delete the "id" utilisateur.
     *
     * @param id the id of the utilisateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/utilisateurs/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        log.debug("REST request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
//        utilisateurSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Utilisateur", false, ENTITY_NAME, id.toString())).build();
    }
   
}
