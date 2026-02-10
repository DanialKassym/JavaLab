package com.springproject.Spring.CRUD;

import com.springproject.Spring.Users.UserRepository;
import com.springproject.Spring.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class Controllers {
    private UserRepository userRepository;

    @Autowired
    public Controllers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<Users> GetUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user/{username}")
    public ResponseEntity<String> CreateUser(@PathVariable("username") String username) {
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(15);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Users user = new Users(username,date);
        userRepository.save(user);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/user/update/{username}")
    public ResponseEntity<String> UpdateUser(@PathVariable("username") String username,@RequestParam("newuser") String newUsername) {
        Users users = userRepository.findByusername(username);
        if (users == null){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok("user deleted");
    }

    @DeleteMapping("/user/delete/{username}")
    public ResponseEntity<String> DeleteUser(@PathVariable("username") String username) {
        Users users = userRepository.findByusername(username);
        if (users == null){
            return ResponseEntity.badRequest().build();
        }
        userRepository.deleteById(users.getId());
        return ResponseEntity.ok("user deleted");
    }
}
