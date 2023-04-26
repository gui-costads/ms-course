package com.gc.hruser.resources;

import com.gc.hruser.entities.User;
import com.gc.hruser.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    ResponseEntity<List<User>> findAll(){
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok().body(userList);
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<User> findById(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/search")
    ResponseEntity<User> findById(@RequestParam String email){
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }
}
