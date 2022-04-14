package com.codegym.demo.repository;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?

    Boolean existsByEmail(String email); //email da co trong DB chua?

    Boolean existsByCompanyName(String companyName);

    Iterable<Company> findAllByEnabledOrderByIdAsc(Boolean enable);

    Iterable<Company> findAllByRecommended(Boolean recommend);



}
