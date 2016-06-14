package org.wizindia.black.controller;

import com.wordnik.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.service.FileService;

/**
 * Created by nischal.k on 07/01/16.
 */
@RestController
@RequestMapping(value = "/v1/anon")
@Api(value = "Anonymous Controller", description = "No Oauth Security for /v1/anon Controller")
public class AnonymousController {
    @Autowired
    FileService fileService;

    final static Logger logger = LoggerFactory.getLogger(AnonymousController.class);

    @RequestMapping(value = "/file/{finalContext}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("finalContext") String finalContext) throws Exception{
        logger.info("Feed upload request recieved with payload: " + finalContext);
        return new FileSystemResource(fileService.getFile(finalContext));
    }


    @RequestMapping(value = "/file", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"multipart/*"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FileUploadResponse uploadFile(@RequestParam(value = "context", defaultValue = "") String context, @RequestParam(value = "context", defaultValue = "") String fileName, @RequestPart(value = "file") MultipartFile file) throws Exception{
        logger.info("Feed upload request recieved with payload: " + file.getOriginalFilename() + " with size: " + file.getSize());
        if(StringUtils.isEmpty(fileName)) {
            fileName = file.getOriginalFilename();
        }
        FileUploadResponse fileUploadResponse = fileService.saveFile(fileName, file, context);
        logger.info("returning path: " + fileUploadResponse);
        return fileUploadResponse;
    }

    @Bean
    public CommonsMultipartResolver filterMultipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        return new MultipartFilter();
    }



}
