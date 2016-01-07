package org.wizindia.black.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/file/{finalContext}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("finalContext") String finalContext) throws Exception{
        logger.info("Feed upload request recieved with payload: " + finalContext);
        return new FileSystemResource(fileService.getFile(finalContext));
    }


}
