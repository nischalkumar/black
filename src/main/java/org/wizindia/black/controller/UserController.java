package org.wizindia.black.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.wizindia.black.common.output.UserResponse;
import org.wizindia.black.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hari_om on 6/14/15.
 */
@RestController
@RequestMapping(value = "/v1/user")
@Api(value = "User", description = "User API")
public class UserController extends AuthController{

    @Autowired
    UserService userService;

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
    http://127.0.0.1:8090/v1/user/admin
    output:
    {
        "id": 1,
        "name": "Admin",
        "login": "admin",
        "password": "check",
        "role": "ADMIN"
    }
     */
    @RequestMapping(value = "/{loginName}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@AuthenticationPrincipal @PathVariable String loginName,
                             HttpServletRequest request, HttpServletResponse response) {
        return userService.getUser(loginName, getUser(SecurityContextHolder.getContext().getAuthentication()));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUser(getUser(SecurityContextHolder.getContext().getAuthentication()));
    }

    /*:
    url: http://127.0.0.1:8090/v1/user
    Payload:
    {
      "firstName" : "nischal",
      "lastName" : "kumar",
      "login" : "erdtrdeyhjgdsfhfgyhjk",
      "password" : "check",
      "role" : ["STUDENT"]
    }
    OutPut:
    {
      "firstName" : "nischal",
      "lastName" : "kumar",
      "login" : "erdtrdeyhjgdsfhfgyhjk",
      "password" : "",
      "role" : ["STUDENT"]
    }
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse insertUser(@AuthenticationPrincipal@RequestBody UserResponse encryptedUser,
                               HttpServletRequest request, HttpServletResponse response) {
        return userService.insertUser(encryptedUser, getUser(SecurityContextHolder.getContext().getAuthentication()));
    }
}