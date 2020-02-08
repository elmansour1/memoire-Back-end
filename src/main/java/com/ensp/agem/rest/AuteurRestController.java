/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.AuteurRepository;
import com.ensp.agem.data.Auteur;
import com.ensp.agem.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author mansour <faouzielmansour@yahoo.com>
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuteurRestController {
    
    private final Logger log = LoggerFactory.getLogger(AuteurRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_auteur";
    
    @Autowired
    private AuteurRepository auteurRepository;

    public AuteurRestController(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }
    
    /**
     * {@code POST  /auteurs} : Create a new auteur.
     *
     * @param auteur the auteur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auteur, or with status {@code 400 (Bad Request)} if the auteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/auteurs")
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) throws URISyntaxException {
        log.debug("REST request to save Auteur : {}", auteur);
        if (auteur.getId() != null) {
            throw new BadRequestAlertException("A new auteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        auteur.setActive(1);
        Auteur result = auteurRepository.save(auteur);
     //   auteurSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/auteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Auteur", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /auteurs} : Updates an existing auteur.
     *
     * @param auteur the auteur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auteur,
     * or with status {@code 400 (Bad Request)} if the auteur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auteur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/auteurs")
    public ResponseEntity<Auteur> updateAuteur(@RequestBody Auteur auteur) throws URISyntaxException {
        log.debug("REST request to update Auteur : {}", auteur);
        if (auteur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Auteur result = auteurRepository.save(auteur);
//        auteurSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Auteur", false, ENTITY_NAME, auteur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /auteurs} : get all the auteurs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auteurs in body.
     */
    @GetMapping("/auteurs")
    public List<Auteur> getAllAuteurs() {
        log.debug("REST request to get all Auteurs");
        return auteurRepository.findAll();
    }

    /**
     * {@code GET  /auteurs/:id} : get the "id" auteur.
     *
     * @param id the id of the auteur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auteur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/auteurs/{id}")
    public ResponseEntity<Auteur> getAuteur(@PathVariable Long id) {
        log.debug("REST request to get Auteur : {}", id);
        Optional<Auteur> auteur = auteurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auteur);
    }

    /**
     * {@code DELETE  /auteurs/:id} : delete the "id" auteur.
     *
     * @param id the id of the auteur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/auteurs/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        log.debug("REST request to delete Auteur : {}", id);
        auteurRepository.deleteById(id);
//        auteurSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Auteur", false, ENTITY_NAME, id.toString())).build();
    }
    
}
