/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.DepartementRepository;
import com.ensp.agem.data.Departement;
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
public class DepartementRestController {
     private final Logger log = LoggerFactory.getLogger(DepartementRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_departement";
    
    @Inject
    private DepartementRepository departementRepository;

    public DepartementRestController(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }
    
    /**
     * {@code POST  /departements} : Create a new departement.
     *
     * @param departement the departement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new departement, or with status {@code 400 (Bad Request)} if the departement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departements")
    public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) throws URISyntaxException {
        log.debug("REST request to save Departement : {}", departement);
        if (departement.getId() != null) {
            throw new BadRequestAlertException("A new departement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        departement.setActive(1);
        Departement result = departementRepository.save(departement);
     //   departementSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/departements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Departement", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /departements} : Updates an existing departement.
     *
     * @param departement the departement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departement,
     * or with status {@code 400 (Bad Request)} if the departement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the departement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departements")
    public ResponseEntity<Departement> updateDepartement(@RequestBody Departement departement) throws URISyntaxException {
        log.debug("REST request to update Departement : {}", departement);
        if (departement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Departement result = departementRepository.save(departement);
//        departementSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Departement", false, ENTITY_NAME, departement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /departements} : get all the departements.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departements in body.
     */
    @GetMapping("/departements")
    public List<Departement> getAllDepartements() {
        log.debug("REST request to get all Departements");
        return departementRepository.findAll();
    }

    /**
     * {@code GET  /departements/:id} : get the "id" departement.
     *
     * @param id the id of the departement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the departement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departements/{id}")
    public ResponseEntity<Departement> getDepartement(@PathVariable Long id) {
        log.debug("REST request to get Departement : {}", id);
        Optional<Departement> departement = departementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(departement);
    }

    /**
     * {@code DELETE  /departements/:id} : delete the "id" departement.
     *
     * @param id the id of the departement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departements/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        log.debug("REST request to delete Departement : {}", id);
        departementRepository.deleteById(id);
//        departementSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Departement", false, ENTITY_NAME, id.toString())).build();
    }
}
