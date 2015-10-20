package org.wizindia.black.utils;

/**
 * Created by hari_om on 6/14/15.
 */

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.wizindia.black.exception.DataFormatException;
import org.wizindia.black.exception.ResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is meant to be extended by all REST resource "controllers".
 * It contains exception mapping and other common REST API functionality
 */
//@ControllerAdvice?
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {
    protected ApplicationEventPublisher eventPublisher;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public
    @ResponseBody
    RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        return new RestErrorInfo(ex, "You messed up.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    //todo: replace with exception mapping
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }

}