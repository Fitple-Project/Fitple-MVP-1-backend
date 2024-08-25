package com.sparta.fitpleprojectbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class HealthCheckController {

  @GetMapping("/healthcheck")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("Server is running");
  }
}
