package com.codegym.demo.controller;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.service.company.ICompanyService;
import com.codegym.demo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    @Autowired
    ICompanyService companyService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;

    @GetMapping
    public ResponseEntity<Iterable<Company>> findAll() {
        List<Company> companies = (List<Company>) companyService.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companyOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> edit(@PathVariable Long id, @RequestBody Company company) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company = companyService.changeInfo(id, company);
        companyService.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/permit")
    public ResponseEntity<List<Company>> findAllUnenable() {
        List<Company> companies = (List<Company>) companyService.getEnableCompanies(false);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/enable/{id}")
    public ResponseEntity<?> enable(@PathVariable Long id) {
        Company company = companyService.setEnable(id);
        try {
            emailService.sendVerificationEmailCompany(company);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(company.isEnabled(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Company>> findAllEnable() {
        List<Company> companies = (List<Company>) companyService.getEnableCompanies(true);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/recommend/{id}")
    public ResponseEntity<?> recommend(@PathVariable Long id) {
        Company company = companyService.changeRecommend(id);
        return new ResponseEntity<>(company.isRecommended(), HttpStatus.OK);
    }

    @GetMapping("/main-page-recommended")
    public ResponseEntity<?> find8CompanyRecommended() {
        List<Company> companies = companyService.get8RecommendedCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/all-recommended")
    public ResponseEntity<?> findAllCompanyRecommended() {
        List<Company> companies = companyService.getAllRecommendedCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

}
