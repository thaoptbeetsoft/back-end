package com.codegym.demo.repository;

import com.codegym.demo.model.WorkForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFormRepository extends JpaRepository<WorkForm, Long> {
}
