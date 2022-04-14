package com.codegym.demo.repository;

import com.codegym.demo.model.Exp;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpRepository extends PagingAndSortingRepository<Exp, Long> {
    Iterable<Exp>findAllByUser_id(Long id);
}