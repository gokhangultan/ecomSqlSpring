package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.entity.ApplicationUser;
import com.ecom.gg.ecomgg.entity.Role;
import com.ecom.gg.ecomgg.repository.RoleRepository;
import com.ecom.gg.ecomgg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ApplicationUser register(String fullName, String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        ApplicationUser user = new ApplicationUser();
        user.setPassword(encodedPassword);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setAuthorities(roles);

        return userRepository.save(user);
    }
}
