/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.EnseignantRepository;
import com.ensp.agem.data.Enseignant;
import com.ensp.agem.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
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
 * @author mansour
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EnseignantRestController {
    private final Logger log = LoggerFactory.getLogger(EnseignantRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_enseignant";
    
    @Autowired
    private EnseignantRepository enseignantRepository;
    
    public EnseignantRestController(EnseignantRepository enseignantRepository){
         this.enseignantRepository = enseignantRepository;
    }
    
    /**
     * {@code POST  /enseignants} : Create a new enseignant.
     *
     * @param enseignant the enseignant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enseignant, or with status {@code 400 (Bad Request)} if the enseignant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enseignants")
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) throws URISyntaxException {
        log.debug("REST request to save Enseignant : {}", enseignant);
        if (enseignant.getId() != null) {
            throw new BadRequestAlertException("A new enseignant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        enseignant.setActive(1);
        Enseignant result = enseignantRepository.save(enseignant);
     //   enseignantSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/enseignants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Enseignant", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enseignants} : Updates an existing enseignant.
     *
     * @param enseignant the enseignant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enseignant,
     * or with status {@code 400 (Bad Request)} if the enseignant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enseignant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enseignants")
    public ResponseEntity<Enseignant> updateEnseignant(@RequestBody Enseignant enseignant) throws URISyntaxException {
        log.debug("REST request to update Enseignant : {}", enseignant);
        if (enseignant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Enseignant result = enseignantRepository.save(enseignant);
//        enseignantSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Enseignant", false, ENTITY_NAME, enseignant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enseignants} : get all the enseignants.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enseignants in body.
     */
    @GetMapping("/enseignants")
    public List<Enseignant> getAllEnseignants() {
        log.debug("REST request to get all Enseignants");
        return enseignantRepository.findAll();
    }

    /**
     * {@code GET  /enseignants/:id} : get the "id" enseignant.
     *
     * @param id the id of the enseignant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enseignant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enseignants/{id}")
    public ResponseEntity<Enseignant> getEnseignant(@PathVariable Long id) {
        log.debug("REST request to get Enseignant : {}", id);
        Optional<Enseignant> enseignant = enseignantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(enseignant);
    }

    /**
     * {@code DELETE  /enseignants/:id} : delete the "id" enseignant.
     *
     * @param id the id of the enseignant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enseignants/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        log.debug("REST request to delete Enseignant : {}", id);
        enseignantRepository.deleteById(id);
//        enseignantSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Enseignant", false, ENTITY_NAME, id.toString())).build();
    }
}
