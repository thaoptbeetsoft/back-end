package com.codegym.demo.controller;

import com.codegym.demo.model.Gender;
import com.codegym.demo.model.WorkForm;
import com.codegym.demo.service.workform.IWorkFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/work-forms")
@CrossOrigin("*")
public class WorkFormController {
    @Autowired
    IWorkFormService workFormService;

    @GetMapping
    public ResponseEntity<Iterable<WorkForm>> findAll() {
        List<WorkForm> workForms = (List<WorkForm>) workFormService.findAll();
        if (workForms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workForms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkForm> findById(@PathVariable Long id) {
        Optional<WorkForm> workForm = workFormService.findById(id);
        if (!workForm.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workForm.get(), HttpStatus.OK);
    }
}
