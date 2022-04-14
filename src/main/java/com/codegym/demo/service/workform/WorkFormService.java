package com.codegym.demo.service.workform;

import com.codegym.demo.model.WorkForm;
import com.codegym.demo.repository.WorkFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkFormService implements IWorkFormService {
    @Autowired
    WorkFormRepository workFormRepository;

    @Override
    public Iterable<WorkForm> findAll() {
        return workFormRepository.findAll();
    }

    @Override
    public Optional<WorkForm> findById(Long id) {
        return workFormRepository.findById(id);
    }

    @Override
    public WorkForm save(WorkForm workForm) {
        return workFormRepository.save(workForm);
    }

    @Override
    public void remove(Long id) {
        workFormRepository.deleteById(id);
    }
}
