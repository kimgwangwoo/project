package com.woo.project.service;

import com.woo.project.model.Role;
import com.woo.project.model.User;
import com.woo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){

        // 패스워드 암호화
        String encodedPassword =passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role=new Role();
        role.setId(1l);
        user.getRoles().add(role);

        return userRepository.save(user);
    }
    public void updateEnabled(Long id, boolean isActive) {
        userRepository.updateEnabled(id, isActive);
    }
}
