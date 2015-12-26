package org.wizindia.black.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.common.request.FileUploadRequest;
import org.wizindia.black.common.response.FileUploadResponse;
import org.wizindia.black.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created by nischal.k on 07/12/15.
 */
@RestController
@RequestMapping(value = "/v1/file")
@Api(value = "file", description = "Feed Upload/Download Controller")
public class FileController extends AuthController {

    @Autowired
    FileService fileService;

    final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("file_name") String fileName, @RequestParam("context") String context, HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.info("Feed upload request recieved with payload: " + file.getOriginalFilename() + " with size: " + file.getSize());
        return fileService.saveFile(getUser(SecurityContextHolder.getContext().getAuthentication()), fileName, file, context);
    }

    @RequestMapping(value = "/{finalContext}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("finalContext") String finalContext, HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.info("Feed upload request recieved with payload: " + finalContext);
        return new FileSystemResource(fileService.getFile(finalContext));
    }

//    @RequestMapping(value = "/auth/{fileName}/{finalContext}", method = RequestMethod.GET, produces = {"application/json"})
//    @ResponseStatus(HttpStatus.OK)
//    public FileUploadResponse getAuthFile(@AuthenticationPrincipal @PathVariable("finalContext") String finalContext, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        logger.info("Feed upload request recieved with payload: " + finalContext);
//        return fileService.getFile(getUser(SecurityContextHolder.getContext().getAuthentication()), finalContext);
//    }

    @RequestMapping(value = "/context", method = RequestMethod.POST)
    @ResponseBody
    public ContextRequest saveContext(@AuthenticationPrincipal @RequestBody ContextRequest contextRequest, HttpServletRequest request, HttpServletResponse response) {
        logger.info("request recieved to save "+ contextRequest);
        return fileService.saveContext(getUser(SecurityContextHolder.getContext().getAuthentication()), contextRequest);
    }
}
