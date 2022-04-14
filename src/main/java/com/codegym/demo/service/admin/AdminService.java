package com.codegym.demo.service.admin;

import com.codegym.demo.model.Admin;
import com.codegym.demo.repository.AdminRepository;
import com.codegym.demo.security.principal.AdminPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public void remove(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = findByEmail(username);
        if (!admin.isPresent()) throw new UsernameNotFoundException(username);
        return AdminPrinciple.build(admin.get());
    }
}
