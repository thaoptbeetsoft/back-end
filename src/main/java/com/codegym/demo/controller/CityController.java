package com.codegym.demo.controller;

import com.codegym.demo.model.City;
import com.codegym.demo.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin("*")
public class CityController {
    @Autowired
    private ICityService iCityService;

    @GetMapping
    public ResponseEntity<Iterable<City>> findAll() {
        List<City> cities = (List<City>) iCityService.findAll();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
