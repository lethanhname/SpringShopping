package com.example.shopping.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {

  @RequestMapping("/check")
  public ResponseEntity<String> check(){
    return ResponseEntity.ok("OK");
  }
}
