package com.springproject.Spring.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ApiController {
    @GetMapping("/api/{username}")
    public ResponseEntity<String> getUsername(@PathVariable("username") String username){
        return ResponseEntity.ok("REST - Your entered name is: " +username);
    }
    @GetMapping("/api")
    public ResponseEntity<String> getMessage(@RequestParam("message") String message){
        return ResponseEntity.ok("REST - Your entered message is: "+message);
    }
}
