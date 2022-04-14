package com.codegym.demo.controller;

import com.codegym.demo.model.Gender;
import com.codegym.demo.service.gender.IGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genders")
@CrossOrigin("*")
public class GenderController {
    @Autowired
    private IGenderService genderService;

    @GetMapping
    public ResponseEntity<Iterable<Gender>> findAll() {
        List<Gender> genders = (List<Gender>) genderService.findAll();
        if (genders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gender> findById(@PathVariable Long id) {
        Optional<Gender> gender = genderService.findById(id);
        if (!gender.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gender.get(), HttpStatus.OK);
    }
}
