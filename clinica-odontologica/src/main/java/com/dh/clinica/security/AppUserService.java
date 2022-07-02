package com.dh.clinica.security;

import com.dh.clinica.entity.AppUser;
import com.dh.clinica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        AppUser appUser = userRepository.findByEmail(email).get();
        return appUser;
    }
}
