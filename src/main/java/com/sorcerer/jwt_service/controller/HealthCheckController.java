package com.sorcerer.jwt_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping("/status")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok("jwt-service application is running");
    }
}
