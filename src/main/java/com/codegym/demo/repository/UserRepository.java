package com.codegym.demo.repository;

import com.codegym.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?
    Boolean existsByEmail(String email); //email da co trong DB chua?
}
