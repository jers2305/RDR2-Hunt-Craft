package com.RDR2Hunt.Craft.spring_boot.controllers;

import com.RDR2Hunt.Craft.spring_boot.dto.ApiDelivery;
import com.RDR2Hunt.Craft.spring_boot.dto.LoginRequest;
import com.RDR2Hunt.Craft.spring_boot.models.Users;
import com.RDR2Hunt.Craft.spring_boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UserService usersService;

    // localhost:8080/api/users -> si no hay parámetro en GetMapping
    // localhost:8080/api/users/get-users
    @GetMapping("/get-users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = this.usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    // localhost:8080/api/users/create
    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = usersService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest credentials){
        ApiDelivery respoonse = this.usersService.login(credentials.getEmail(),credentials.getPassword());
        return ResponseEntity.status(respoonse.getStatus()).body(respoonse);

    }

}

