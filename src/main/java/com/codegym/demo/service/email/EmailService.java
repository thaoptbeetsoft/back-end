package com.codegym.demo.service.email;

import com.codegym.demo.model.Admin;
import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.codegym.demo.service.admin.IAdminService;
import com.codegym.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;


@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    IUserService userService;

    @Autowired
    IAdminService adminService;

    public void sendVerificationEmail(User user)
            throws MessagingException {
        String subject = "Register notification";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getName() + ",</p>";
        mailContent += "<p> Bạn đã đăng ký thành công </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom(senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendVerificationEmailCompany(Company company)
            throws MessagingException {
        String subject = "Register notification";
        String senderName = "admin";
        String mailContent = "<p>Dear " + company.getCompanyName() + ",</p>";
        mailContent += "<p> Bạn đã đăng ký thành công </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom(senderName);
        helper.setTo(company.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }
    public String getType(String email){
        Optional<User> user = userService.findByEmail(email);
        Optional<Admin> admin = adminService.findByEmail(email);
        if (user.isPresent()){
            System.out.println(admin.getClass().getTypeName().toString());
            return user.get().getType().toString();
        }else if (admin.isPresent()){
            System.out.println(admin.get().getType().toString());
            return admin.get().getType().toString();
        }else {
            return "";
        }
    }
}
