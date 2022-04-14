package com.codegym.demo.service.cv;

import com.codegym.demo.model.CV;
import com.codegym.demo.repository.ICVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CVService implements ICVService{
    @Autowired
    private ICVRepository icvRepository;
    @Override
    public Iterable<CV> findAll() {
        return icvRepository.findAll();
    }

    @Override
    public Optional<CV> findById(Long id) {
        return icvRepository.findById(id);
    }

    @Override
    public CV save(CV cv) {
        return icvRepository.save(cv);
    }

    @Override
    public void remove(Long id) {
        icvRepository.deleteById(id);
    }

    @Override
    public Iterable<CV> getAllByUserId(Long id) {
        return icvRepository.getAllByUserId(id);
    }
}
