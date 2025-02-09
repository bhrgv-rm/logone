package com.logone.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.logone.backend.Service.JWTService;
import com.logone.backend.Service.UserService;
import com.logone.backend.Model.UserModel;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JWTService jwtService;

  @GetMapping("/all")
  public ResponseEntity<List<UserModel>> getAllUsers() {
    return ResponseEntity.status(400).body(userService.getAllUsers());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserDetails> findByEmail(@PathVariable String email) {
    return ResponseEntity.status(400).body(userService.findByEmail(email));
  }

  @PostMapping("/create")
  public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
    if (userService.findByEmail(user.getEmail()) != null) {
      return ResponseEntity.status(400).body(null);
    }
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserModel createdUser = userService.createUser(user);
    return ResponseEntity.status(201).body(createdUser);
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody UserModel user) {
    UserDetails existingUser = userService.findByEmail(user.getEmail());
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
      return ResponseEntity.ok("Login successful");
    } else {
      return ResponseEntity.status(401).body("Invalid email or password");
    }
  }

  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticateUser(@RequestBody UserModel user) {
    UserDetails existingUser = userService.findByEmail(user.getEmail());
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
      return ResponseEntity.ok("Login successful");
    } else {
      return ResponseEntity.status(401).body("Invalid email or password");
    }
  }

  @PostMapping("/authenticate/token")
  public String authenticateAndGetToken(@RequestBody UserModel user) {
    UserModel existingUser = (UserModel) userService.findByEmail(user.getEmail());
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(existingUser.getEmail(), existingUser.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(existingUser);
    } else {
      throw new UsernameNotFoundException("Invalid email or password");
    }
  }

  @Configuration
  public static class AuthenticationManagerConfig {

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
      AuthenticationManagerBuilder authenticationManagerBuilder = http
          .getSharedObject(AuthenticationManagerBuilder.class);
      authenticationManagerBuilder.userDetailsService(userService::findByEmail)
          .passwordEncoder(new BCryptPasswordEncoder());
      return authenticationManagerBuilder.build();
    }
  }
}
