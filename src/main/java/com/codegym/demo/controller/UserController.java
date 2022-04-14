package com.codegym.demo.controller;

import com.codegym.demo.model.User;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> findAll() {
        List<User> userIterable = (List<User>) userService.findAll();
        if (userIterable.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user){
        Optional< User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setEnabled(userOptional.get().getEnabled());
        user.setEmail(userOptional.get().getEmail());
        user.setPassword(userOptional.get().getPassword());
        user.setEnabled(userOptional.get().getEnabled());
        user.setType(userOptional.get().getType());
        user.getName();
        user.setId(id);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}