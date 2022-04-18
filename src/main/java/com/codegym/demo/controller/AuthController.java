package com.codegym.demo.controller;

import com.codegym.demo.constant.Constant;
import com.codegym.demo.dto.request.*;
import com.codegym.demo.dto.response.JwtResponse;
import com.codegym.demo.dto.response.Response;
import com.codegym.demo.model.Admin;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.security.jwt.AdminJwtService;
import com.codegym.demo.security.jwt.CompanyJwtService;
import com.codegym.demo.security.jwt.UserJwtService;
import com.codegym.demo.security.principal.AdminPrinciple;
import com.codegym.demo.security.principal.CompanyPrinciple;
import com.codegym.demo.security.principal.UserPrinciple;
import com.codegym.demo.service.admin.IAdminService;
import com.codegym.demo.service.company.CompanyService;
import com.codegym.demo.service.email.EmailService;
import com.codegym.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codegym.demo.dto.response.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserJwtService userJwtService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyJwtService companyJwtService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private AdminJwtService adminJwtService;

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegisterForm registerForm,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByEmail(registerForm.getEmail()) // tìm kiếm xem trong database có email không
                    || userService.existsByEmail(registerForm.getEmail())// tìm kiếm xem trong database có email không
                    || adminService.existsByEmail(registerForm.getEmail())) {// tìm kiếm xem trong database có email không
                return new ResponseEntity<>(new ResponseBody
                        (Response.EMAIL_IS_EXISTS, null),
                        HttpStatus.CONFLICT);
            }
            User user = userService.register(registerForm);//lưu vào database
            if (user != null) {
                emailService.sendVerificationEmail(user);
            }
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, user),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/companies/register")
    public ResponseEntity<ResponseBody> registerCompany(@Validated @RequestBody CompanyRegisterForm registerForm,
                                                        BindingResult bindingResult,
                                                        HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByCompanyName(registerForm.getCompanyName())) {
                return new ResponseEntity<>(new ResponseBody(Response.NAME_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            if (companyService.existsByEmail(registerForm.getEmail())// tìm kiếm xem trong database có email không
                    || userService.existsByEmail(registerForm.getEmail()) // tìm kiếm xem trong database có email không
                    || adminService.existsByEmail(registerForm.getEmail())) {// tìm kiếm xem trong database có email không
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            Company company = companyService.register(registerForm);//lưu vào database
            String companyCode = company.getShortName().substring(0, 3) + company.getId() + (int) (Math.random() * (9999 - 1000) + 1000);
            company.setCompanyCode(companyCode);
            Company company1 = companyService.save(company);
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, company1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/companies/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody CompanyLoginForm companyLoginForm) {
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken
                            (companyLoginForm.getEmail(), companyLoginForm.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = companyJwtService.generateTokenLogin(authentication);

            CompanyPrinciple companyPrinciple = (CompanyPrinciple) authentication.getPrincipal();

            Company company = companyService.findByEmail(companyLoginForm.getEmail()).get();
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, new JwtResponse(company.getId(), jwt, company.getCompanyName(),
                            companyPrinciple.getAuthorities())),
                    HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/users/login")
    public ResponseEntity<ResponseBody> login(@Validated @RequestBody UserLoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (emailService.getType(loginForm.getEmail()).equals(Constant.TypeName.ADMIN.toString())) {
                String jwt = adminJwtService.generateTokenLogin(authentication);
                AdminPrinciple adminPrinciple = (AdminPrinciple) authentication.getPrincipal();
                Admin currentAdmin = adminService.findByEmail(loginForm.getEmail()).get();
                return new ResponseEntity<>(new ResponseBody(Response.SUCCESS,
                        new JwtResponse(currentAdmin.getId(), jwt, currentAdmin.getName(), adminPrinciple.getAuthorities())),
                        HttpStatus.OK);
            } else {
                String jwt = userJwtService.generateTokenLogin(authentication);
                UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
                User currentUser = userService.findByEmail(loginForm.getEmail()).get();
                return new ResponseEntity<>(new ResponseBody(Response.SUCCESS,
                        new JwtResponse(currentUser.getId(), jwt, currentUser.getName(), userPrinciple.getAuthorities())),
                        HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_NOT_FOUND, null), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/admins/register")
    public ResponseEntity<?> registerAdmin(@Validated @RequestBody AdminRegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        try {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
            }
            if (companyService.existsByEmail(registerForm.getEmail()) || userService.existsByEmail(registerForm.getEmail()) || adminService.existsByEmail(registerForm.getEmail())) {
                return new ResponseEntity<>(new ResponseBody(Response.EMAIL_IS_EXISTS, null), HttpStatus.CONFLICT);
            }
            Admin admin = new Admin(registerForm.getName(), registerForm.getEmail(), registerForm.getPassword());
            admin.setType(Constant.TypeName.ADMIN);
            Admin admin1 = adminService.save(admin);
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, admin1), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getType")
    public ResponseEntity<?> getType(String email) {
        return new ResponseEntity<>(emailService.getType(email), HttpStatus.OK);
    }

    @PostMapping("/companies/{id}/change-password")
    public ResponseEntity<?> changeCompanyPassword(@Valid @RequestBody CompanyPasswordForm companyPasswordForm, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        Optional<Company> company = companyService.findById(id);
        if (!company.isPresent()) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.NOT_FOUND);
        }
        if (companyPasswordForm.getNewPassword().trim().equals(companyPasswordForm.getCurrentPassword().trim())) {
            return new ResponseEntity<>(new ResponseBody(Response.NEW_PASSWORD_IS_DUPLICATED, null), HttpStatus.CONFLICT);
        }
        boolean matches = passwordEncoder.matches(companyPasswordForm.getCurrentPassword(), company.get().getPassword());
        if (companyPasswordForm.getNewPassword() != null) {
            if (matches) {
                company.get().setPassword(passwordEncoder.encode(companyPasswordForm.getNewPassword().trim()));
                companyService.save(company.get());
            } else {
                return new ResponseEntity<>(new ResponseBody(Response.PASSWORD_IS_NOT_TRUE, null), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, company.get()), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/change-password")
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody UserPasswordForm userPasswordForm, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(new ResponseBody(Response.OBJECT_INVALID, null), HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, null), HttpStatus.NOT_FOUND);
        }
        if (userPasswordForm.getNewPassword().trim().equals(userPasswordForm.getCurrentPassword().trim())) {
            return new ResponseEntity<>(new ResponseBody(Response.NEW_PASSWORD_IS_DUPLICATED, null), HttpStatus.CONFLICT);
        }
        boolean matches = passwordEncoder.matches(userPasswordForm.getCurrentPassword(), user.get().getPassword());
        if (userPasswordForm.getNewPassword() != null) {
            if (matches) {
                user.get().setPassword(passwordEncoder.encode(userPasswordForm.getNewPassword().trim()));
                userService.save(user.get());
            } else {
                return new ResponseEntity<>(new ResponseBody(Response.PASSWORD_IS_NOT_TRUE, null), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, user.get()), HttpStatus.OK);
    }
}