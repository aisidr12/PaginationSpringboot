package com.paginationpractice.demo.controller;

import com.paginationpractice.demo.request.AuthenticationRequest;
import com.paginationpractice.demo.security.JwtTokenUtil;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *  This is a controller which is responsible to create a token to access to the resources
 *  this is the only available resource without authentication, otherwise will return 401 unauthorized
 */

@RestController
@RequestMapping("/api")
public class AuthController {

  private final JwtTokenUtil jwtUtil;

  public AuthController(JwtTokenUtil util) {
    this.jwtUtil = util;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) {
    if ("admin".equals(authenticationRequest.getUsername())) {
      String token = jwtUtil.generateToken(authenticationRequest.getUsername());
      return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
    return ResponseEntity.status(401).body("Credenciales incorrectas");
  }

}
