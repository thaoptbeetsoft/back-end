package com.codegym.demo.service.user;

import com.codegym.demo.constant.Constant;
import com.codegym.demo.dto.request.UserRegisterForm;
import com.codegym.demo.model.User;
import com.codegym.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.codegym.demo.security.principal.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User register(UserRegisterForm userRegisterForm) {
        User user = new User();
        user.setName(userRegisterForm.getName().trim());
        user.setEmail(userRegisterForm.getEmail().trim());
        String encode = passwordEncoder.encode(userRegisterForm.getPassword().trim());//mã hoá mật khẩu đăng kí
        user.setPassword(encode);
        user.setPhone(userRegisterForm.getPhone());
        user.setType(Constant.TypeName.USER);
        user.setEnabled(false);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByEmail(username);
        if (!user.isPresent()) throw new UsernameNotFoundException(username);
        return UserPrinciple.build(user.get());
    }


}
