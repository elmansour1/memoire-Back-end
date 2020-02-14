/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.dao.AuteurRepository;
import com.ensp.agem.dao.DepartementRepository;
import com.ensp.agem.dao.EnseignantRepository;
import com.ensp.agem.dao.MemoireRepository;
import com.ensp.agem.dao.ParcoursRepository;
import com.ensp.agem.dao.SpecialisationRepository;
import com.ensp.agem.data.Auteur;
import com.ensp.agem.data.Enseignant;
import com.ensp.agem.data.Memoire;
import com.ensp.agem.errors.BadRequestAlertException;
import com.ensp.agem.payload.UploadFileResponse;
import com.ensp.agem.service.FileStorageService;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@CrossOrigin("*")
@RestController
//@RequestMapping("/api")
public class MemoireRestController {
    private final Logger log = LoggerFactory.getLogger(MemoireRestController.class);
    private final Logger logger = LoggerFactory.getLogger(MemoireRestController.class);

    private static final String ENTITY_NAME = "gestion_memoire_memoire";
    
    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private SpecialisationRepository specialisationRepository;
    @Autowired
    private ParcoursRepository parcoursRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private MemoireRepository memoireRepository;
    
    @Autowired
    private FileStorageService fileStorageService;
    
//    public MemoireRestController(MemoireRepository memoireRepository, FileStorageService fileStorageService){
//        this.memoireRepository = memoireRepository;
//        this.fileStorageService = fileStorageService;
//    }
    
    /**
     * {@code POST  /memoires} : Create a new memoire.
     *
     * @param memoire the memoire to create.
     * @param titre
     * @param datep
     * @param annee
     * @param motCle
     * @param resume
     * @param abstrat
     * @param encadreurs
     * @param auteur
     * @param file
     * @param session
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memoire, or with status {@code 400 (Bad Request)} if the memoire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PostMapping("/memoires")
    @RequestMapping(value=("/api/memoires"),method=RequestMethod.POST)
    public ResponseEntity<Memoire> createMemoire(@RequestBody Memoire memoire,HttpSession session) throws URISyntaxException { 
        log.debug("REST request to save Memoire : {}", memoire);
        Memoire memory = memoireRepository.findMemoireByTitre(memoire.getTitre());
        if ( memory != null) {
            throw new BadRequestAlertException("Ce memoire existe déjà du moins le titre", ENTITY_NAME, "Memoire existe");
        }else{
        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        memoire.setActive(1);
//        memoire.setTitre(titre);
        memoire.setDatePublication(new Date());
//        memoire.setAnneesSoutenance(annee);
//        memoire.setMotsCles(motCle);
//        memoire.setResume(resume);
//        memoire.setAbstrat(abstrat);
//        String fichier = fileStorageService.storeFile(file);
//        System.out.println(fichier);
//        memoire.setDocument("/downloadFile/"+fileStorageService.storeFile(file));
        memoire.setDocument("/downloadFile/"+memoire.getDocument());

//        memoire.setSpecialisation(specialisation);
//        memoire.setEncadreurs(encadreurs);
//        memoire.setAuteurs(auteurs);
        
        Memoire result = memoireRepository.save(memoire);
     //   memoireSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/memoires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("Memoire", false, ENTITY_NAME, result.getId().toString()))
            .body(result);
        }
    }

    /**
     * {@code PUT  /memoires} : Updates an existing memoire.
     *
     * @param memoire the memoire to update.
     * @param file
     * @param session
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memoire,
     * or with status {@code 400 (Bad Request)} if the memoire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memoire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/memoires")
   @RequestMapping(value=("/api/memoires"),headers=("content-type=multipart/*"),method=RequestMethod.PUT)
    public ResponseEntity<Memoire> updateMemoire(@RequestBody Memoire memoire, @RequestParam("file") MultipartFile file,HttpSession session) throws URISyntaxException {
        log.debug("REST request to update Memoire : {}", memoire);
        if (memoire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if(file.isEmpty()){
            String fichier = fileStorageService.storeFile(file);
            System.out.println(fichier);
            memoire.setDocument("/downloadFile/"+fileStorageService.storeFile(file));
        }
        Memoire result = memoireRepository.save(memoire);
//        memoireSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Memoire", false, ENTITY_NAME, memoire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /memoires} : get all the memoires.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memoires in body.
     */
    @GetMapping("/api/memoires")
    public List<Memoire> getAllMemoires() {
        log.debug("REST request to get all Memoires");
        return (List<Memoire>)memoireRepository.findAll();
    }

    /**
     * {@code GET  /memoires/:id} : get the "id" memoire.
     *
     * @param id the id of the memoire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memoire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api/memoires/{id}")
    public ResponseEntity<Memoire> getMemoire(@PathVariable Long id) {
        log.debug("REST request to get Memoire : {}", id);
        Optional<Memoire> memoire = memoireRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(memoire);
    }

    /**
     * {@code DELETE  /memoires/:id} : delete the "id" memoire.
     *
     * @param id the id of the memoire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api/memoires/{id}")
    public ResponseEntity<Void> deleteMemoire(@PathVariable Long id) {
        log.debug("REST request to delete Memoire : {}", id);
        memoireRepository.deleteById(id);
//        memoireSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("Memoire", false, ENTITY_NAME, id.toString())).build();
    }
   
       
    @PostMapping("/memoire/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
//    @PostMapping("/memoire/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toSet());
//    }
    
    @GetMapping("/memoire/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
