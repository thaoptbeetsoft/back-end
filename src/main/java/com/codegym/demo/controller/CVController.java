package com.codegym.demo.controller;

import com.codegym.demo.model.CV;
import com.codegym.demo.model.Post;
import com.codegym.demo.model.User;
import com.codegym.demo.service.cv.ICVService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cvs")
@CrossOrigin("*")
public class CVController {
    @Autowired
    private ICVService icvService;
    @Autowired
    private IUserService iUserService;
    @GetMapping
    public ResponseEntity<Iterable<CV>> findAll() {
        List<CV> cvs = (List<CV>) icvService.findAll();
        if (cvs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CV> save(@RequestBody CV cv) {
        return new ResponseEntity<>(icvService.save(cv), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<CV>> findByUserId(@PathVariable Long id){
        List<CV> cvs = (List<CV>) icvService.getAllByUserId(id);
        if (cvs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }
}
