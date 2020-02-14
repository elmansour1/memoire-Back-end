/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.rest;

import com.ensp.agem.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author mansour <faouzielmansour@yahoo.com>
 */
@CrossOrigin("*")
@RestController
//@RequestMapping("/api")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileStorageService fileStorageService;
    
//    @PostMapping("/uploadFile")
    @RequestMapping(value=("/api/uploadFile"),headers=("content-type=multipart/*"),method=RequestMethod.POST)
    public FileStorageResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new FileStorageResponse(fileName, fileDownloadUri);
    }
    
//    @Bean
//  public MultipartResolver multipartResolver() {
//    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//    commonsMultipartResolver.setMaxUploadSize(100000);
//    commonsMultipartResolver.setDefaultEncoding("UTF-8");
//    return commonsMultipartResolver;
//  }
}
