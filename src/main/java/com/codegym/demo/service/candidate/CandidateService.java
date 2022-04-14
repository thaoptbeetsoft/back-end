package com.codegym.demo.service.candidate;

import com.codegym.demo.model.Candidate;
import com.codegym.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Iterable<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public void remove(Long id) {
        candidateRepository.deleteById(id);
    }


    @Override
    public Iterable<Candidate> findAllByPostId(Long id) {
        return candidateRepository.findAllByPostId(id);
    }

    @Override
    public Boolean existsByCv_IdAndPostId(Long cvId, Long postId) {
        return candidateRepository.existsByCv_IdAndPostId(cvId, postId);
    }

}
