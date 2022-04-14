package com.codegym.demo.service.company;

import com.codegym.demo.constant.Constant;
import com.codegym.demo.dto.request.CompanyRegisterForm;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.Post;
import com.codegym.demo.model.User;
import com.codegym.demo.repository.CompanyRepository;
import com.codegym.demo.security.principal.CompanyPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void remove(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByCompanyName(String companyName) {
        return companyRepository.existsByCompanyName(companyName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Company> company = findByEmail(username);
        if (!company.isPresent()) throw new UsernameNotFoundException(username);
        return CompanyPrinciple.build(company.get());
    }

    @Override
    public Company changeInfo(Long id, Company company) {
        Optional<Company> companyOptional = findById(id);
        company.setId(id);
        company.setEmail(companyOptional.get().getEmail());
        company.setCompanyCode(companyOptional.get().getCompanyCode());
        company.setType(companyOptional.get().getType());
        company.setEnabled(true);
        company.setRecommended(companyOptional.get().isRecommended());
        if (company.getCompanyName() == null || company.getCompanyName().trim().equals("")) {
            company.setCompanyName(companyOptional.get().getCompanyName());
        }
        if (company.getShortName() == null || company.getShortName().trim().equals("")) {
            company.setShortName(companyOptional.get().getShortName());
        }

        if (company.getAddress() == null || company.getAddress().trim().equals("")) {
            company.setAddress(companyOptional.get().getAddress());
        }
        if (company.getBranch() == null || company.getBranch().trim().equals("")) {
            company.setBranch(companyOptional.get().getBranch());
        }
        if (company.getPassword() == null || company.getPassword().trim().equals("")) {
            company.setPassword(companyOptional.get().getPassword());
        } else {
            String encode = passwordEncoder.encode(company.getPassword());
            company.setPassword(encode);
        }
        if (company.getImage() == null || company.getImage().trim().equals("")) {
            company.setImage(companyOptional.get().getImage());
        }
        if (company.getLinkGoogle() == null || company.getLinkGoogle().trim().equals("")) {
            company.setLinkGoogle(companyOptional.get().getLinkGoogle());
        }
        if (company.getWebsite() == null || company.getWebsite().trim().equals("")) {
            company.setWebsite(companyOptional.get().getWebsite());
        }
        if (company.getDescription() == null || company.getDescription().trim().equals("")) {
            company.setDescription(companyOptional.get().getDescription());
        }
        if (company.getPhone() == null || company.getPhone().trim().equals("")) {
            company.setPhone(companyOptional.get().getPhone());
        }
        if (company.getNumberOfStaff() == 0) {
            company.setNumberOfStaff(companyOptional.get().getNumberOfStaff());
        }
        return company;
    }

    @Override
    public Iterable<Company> getEnableCompanies(Boolean enable) {
        return companyRepository.findAllByEnabledOrderByIdAsc(enable);
    }

    @Override
    public Company register(CompanyRegisterForm companyRegisterForm) {
        Company company = new Company();
        String encode = passwordEncoder.encode(companyRegisterForm.getPassword().trim());
        company.setCompanyName(companyRegisterForm.getCompanyName().trim());
        company.setShortName(companyRegisterForm.getShortName().trim());
        company.setEmail(companyRegisterForm.getEmail().trim());
        company.setPassword(encode);
        company.setDescription(companyRegisterForm.getDescription());
        company.setImage(Constant.IMAGE_COMPANY_DEFAULT);
        company.setType(Constant.TypeName.COMPANY);
        company.setEnabled(false);
        company.setRecommended(false);
        return companyRepository.save(company);
    }

    @Override
    public Company setEnable(long id){
        Optional<Company> company = companyRepository.findById(id);
        company.get().setEnabled(true);
        return companyRepository.save(company.get());
    }

    @Override
    public Company changeRecommend(long id) {
        Optional<Company> company = companyRepository.findById(id);
        company.get().setRecommended(!company.get().isRecommended());
        return companyRepository.save(company.get());
    }

    @Override
    public List<Company> get8RecommendedCompanies() {
        List<Company> companies = new ArrayList<>(8);
        List<Company> companyList = (List<Company>)companyRepository.findAllByRecommended(true);
        for (int i=0;i<companyList.size();i++) {
            if (i<8){
                companies.add(companyList.get(i));
            }else {
                break;
            }
        }
        return companies;
    }

    @Override
    public List<Company> getAllRecommendedCompanies() {
        return (List<Company>)companyRepository.findAllByRecommended(true);
    }
}
