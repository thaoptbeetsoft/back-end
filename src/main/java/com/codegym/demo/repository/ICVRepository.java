package com.codegym.demo.repository;

import com.codegym.demo.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICVRepository extends JpaRepository<CV,Long> {
    Iterable<CV> getAllByUserId(Long id);
}
