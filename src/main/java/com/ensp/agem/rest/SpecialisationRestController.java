/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.SpecialisationRepository;
import com.ensp.agem.data.Departement;
import com.ensp.agem.data.Parcours;
import com.ensp.agem.data.Specialisation;
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
public class SpecialisationRestController {
    private final Logger log = LoggerFactory.getLogger(SpecialisationRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_specialisation";
    
    @Autowired
    private final SpecialisationRepository specialisationRepository;

    public SpecialisationRestController(SpecialisationRepository specialisationRepository) {
        this.specialisationRepository = specialisationRepository;
    }
    
    /**
     * {@code POST  /specialisations} : Create a new specialisation.
     *
     * @param specialisation the specialisation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialisation, or with status {@code 400 (Bad Request)} if the specialisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specialisations")
    public ResponseEntity<Specialisation> createSpecialisation(@RequestBody Specialisation specialisation) throws URISyntaxException {
        log.debug("REST request to save Specialisation : {}", specialisation);
        if (specialisation.getId() != null) {
            throw new BadRequestAlertException("A new specialisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        specialisation.setActive(1);
        specialisation.getParcours().toString();
        Specialisation result = specialisationRepository.save(specialisation);
     //   specialisationSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/specialisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Specialisation", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specialisations} : Updates an existing specialisation.
     *
     * @param specialisation the specialisation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialisation,
     * or with status {@code 400 (Bad Request)} if the specialisation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialisation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialisations")
    public ResponseEntity<Specialisation> updateSpecialisation(@RequestBody Specialisation specialisation) throws URISyntaxException {
        log.debug("REST request to update Specialisation : {}", specialisation);
        if (specialisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        specialisation.setActive(1);
        Specialisation result = specialisationRepository.save(specialisation);
//        specialisationSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Specialisation", false, ENTITY_NAME, specialisation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specialisations} : get all the specialisations.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialisations in body.
     */
    @GetMapping("/specialisations")
    public List<Specialisation> getAllSpecialisations() {
        log.debug("REST request to get all Specialisations");
        return specialisationRepository.findAllSpecialisation();
    }

    /**
     * {@code GET  /specialisations/:id} : get the "id" specialisation.
     *
     * @param id the id of the specialisation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialisation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specialisations/{id}")
    public ResponseEntity<Specialisation> getSpecialisation(@PathVariable Long id) {
        log.debug("REST request to get Specialisation : {}", id);
        Optional<Specialisation> specialisation = specialisationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(specialisation);
    }

    /**
     * {@code DELETE  /specialisations/:id} : delete the "id" specialisation.
     *
     * @param id the id of the specialisation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specialisations/{id}")
    public ResponseEntity<Void> deleteSpecialisation(@PathVariable Long id) {
        log.debug("REST request to delete Specialisation : {}", id);
        Specialisation specialisation = specialisationRepository.getOne(id);
        if(specialisation != null){
            specialisation.setActive(0);
            specialisationRepository.save(specialisation);
        }
//        specialisationRepository.deleteById(id);
//        specialisationSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Specialisation", false, ENTITY_NAME, id.toString())).build();
    }
}
