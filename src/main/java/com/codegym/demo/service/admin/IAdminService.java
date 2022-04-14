package com.codegym.demo.service.admin;

import com.codegym.demo.model.Admin;
import com.codegym.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAdminService extends IGeneralService<Admin>, UserDetailsService {
    Optional<Admin> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?
    Boolean existsByEmail(String email); //email da co trong DB chua?
}
