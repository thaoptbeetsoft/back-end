package com.codegym.demo.repository;

import com.codegym.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Iterable<Candidate> findAllByPostId(Long id);

    Boolean existsByCv_IdAndPostId(Long cvId, Long postId);

}
