package com.securityApp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @PreAuthorize("permitAll()")
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PreAuthorize("hasAuthority('READ') or hasRole('DEVELOPER')")
    @GetMapping("/hello-secured")
    public String helloSecured() {
        return "hello world secured";
    }

    @PreAuthorize("hasAuthority('CREATED')")
    @GetMapping("/hello-secured/created")
    public String helloSecuredCreated() {
        return "hello world secured";
    }


}
