package com.codegym.demo.service.exp;

import com.codegym.demo.model.Exp;
import com.codegym.demo.repository.ExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpService implements IExpService{
    @Autowired
    private ExpRepository expRepository;

    @Override
    public Iterable<Exp> findAll() {
        return expRepository.findAll();
    }

    @Override
    public Optional<Exp> findById(Long id) {
        return expRepository.findById(id);
    }

    @Override
    public Exp save(Exp exp) {
        return expRepository.save(exp);
    }

    @Override
    public void remove(Long id) {
        expRepository.deleteById(id);
    }

    @Override
    public Iterable<Exp> findAllByUser_id(Long id) {
        return expRepository.findAllByUser_id(id);
    }
}