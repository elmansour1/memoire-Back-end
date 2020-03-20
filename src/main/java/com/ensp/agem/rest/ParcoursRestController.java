/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.ParcoursRepository;
import com.ensp.agem.data.Parcours;
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
public class ParcoursRestController {
    private final Logger log = LoggerFactory.getLogger(ParcoursRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_parcour";
    
    @Inject
    private ParcoursRepository parcoursRepository;
    
    public ParcoursRestController(ParcoursRepository parcoursRepository){
        this.parcoursRepository = parcoursRepository;
    }
    
    /**
     * {@code POST  /parcours} : Create a new parcour.
     *
     * @param parcour the parcour to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parcour, or with status {@code 400 (Bad Request)} if the parcour has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parcours")
    public ResponseEntity<Parcours> createParcours(@RequestBody Parcours parcour) throws URISyntaxException {
        log.debug("REST request to save Parcours : {}", parcour);
        if (parcour.getId() != null) {
            throw new BadRequestAlertException("A new parcour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        parcour.setActive(1);
        Parcours result = parcoursRepository.save(parcour);
     //   parcourSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/parcours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Parcours", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parcours} : Updates an existing parcour.
     *
     * @param parcour the parcour to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parcour,
     * or with status {@code 400 (Bad Request)} if the parcour is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parcour couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parcours")
    public ResponseEntity<Parcours> updateParcours(@RequestBody Parcours parcour) throws URISyntaxException {
        log.debug("REST request to update Parcours : {}", parcour);
        if (parcour.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Parcours result = parcoursRepository.save(parcour);
//        parcourSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Parcours", false, ENTITY_NAME, parcour.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parcours} : get all the parcours.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parcours in body.
     */
    @GetMapping("/parcours")
    public List<Parcours> getAllParcourss() {
        log.debug("REST request to get all Parcourss");
        return parcoursRepository.findAllParcours();
    }

    /**
     * {@code GET  /parcours/:id} : get the "id" parcour.
     *
     * @param id the id of the parcour to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parcour, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parcours/{id}")
    public ResponseEntity<Parcours> getParcours(@PathVariable Long id) {
        log.debug("REST request to get Parcours : {}", id);
        Optional<Parcours> parcour = parcoursRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parcour);
    }

    /**
     * {@code DELETE  /parcours/:id} : delete the "id" parcour.
     *
     * @param id the id of the parcour to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parcours/{id}")
    public ResponseEntity<Void> deleteParcours(@PathVariable Long id) {
        log.debug("REST request to delete Parcours : {}", id);
        Parcours parcours = parcoursRepository.getOne(id);
        if(parcours != null){
            parcours.setActive(0);
            parcoursRepository.save(parcours);
        }
//        parcoursRepository.deleteById(id);
//        parcourSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Parcours", false, ENTITY_NAME, id.toString())).build();
    }
}
