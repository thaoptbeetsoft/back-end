package com.codegym.demo.service.gender;

import com.codegym.demo.model.Gender;
import com.codegym.demo.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderService implements IGenderService {
    @Autowired
    GenderRepository genderRepository;

    @Override
    public Iterable<Gender> findAll() {
        return genderRepository.findAll();
    }

    @Override
    public Optional<Gender> findById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    public Gender save(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public void remove(Long id) {
        genderRepository.deleteById(id);
    }
}
