package com.codegym.demo.service.company;

import com.codegym.demo.dto.request.CompanyRegisterForm;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company>, UserDetailsService {
    Optional<Company> findByEmail(String email); //Tim kiem email co ton tai trong DB khong?

    Boolean existsByEmail(String email); //email da co trong DB chua?

    Boolean existsByCompanyName(String companyName); // ten cong ty da co trong DB chua

    Company changeInfo(Long id, Company company);

    Company register(CompanyRegisterForm companyRegisterForm);

    Iterable<Company> getEnableCompanies (Boolean enable);

    Company setEnable(long id);

    Company changeRecommend(long id);

    List<Company> get8RecommendedCompanies ();

    List<Company> getAllRecommendedCompanies ();

}
