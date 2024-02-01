package com.snippets.controllers;

import com.snippets.payload.AuthResponse;
import com.snippets.payload.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import com.snippets.util.JwtTokenUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    private final InMemoryUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public UserController(ApplicationContext context, JwtTokenUtil util) {
        this.userDetailsService = (InMemoryUserDetailsManager) context.getBean("userDetailsService");
        this.passwordEncoder = (PasswordEncoder) context.getBean("passwordEncoder");
    }
    @GetMapping
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userData) {
        if (!userDetailsService.userExists(userData.getUsername())) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        UserDetails user = userDetailsService.loadUserByUsername(userData.getUsername());
        if (!passwordEncoder.matches(userData.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Incorrect Password", HttpStatus.UNAUTHORIZED);
        }
        //this line below throws an error without the dependency: javax.xml.bind:jaxb-api:2.3.0
        String accessToken = jwtTokenUtil.generateAccessToken(userDetailsService.loadUserByUsername(userData.getUsername()));
        AuthResponse response = new AuthResponse(userData.getUsername(), accessToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userData) {
        if (userDetailsService.userExists(userData.getUsername())) {
            return new ResponseEntity<>("Already registered", HttpStatus.CONFLICT);
        }
        UserDetails newUser = User.builder().username(userData.getUsername()).password(passwordEncoder.encode(userData.getPassword())).roles("USER")
                .build();
        userDetailsService.createUser(newUser);
        return new ResponseEntity<String>(newUser.getUsername(), HttpStatus.OK);
    }
}
