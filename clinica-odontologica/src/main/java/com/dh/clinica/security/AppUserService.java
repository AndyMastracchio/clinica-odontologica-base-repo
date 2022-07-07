package com.dh.clinica.security;

import com.dh.clinica.controller.JWTController;
import com.dh.clinica.entity.AppUser;
import com.dh.clinica.entity.AppUserRole;
import com.dh.clinica.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(JWTController.class);

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("=== DENTRO DE loadUserByUsername METHOD en APPUSERSERVICE");
        AppUser appUser = userRepository.findByUsername(username).get();
        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        GrantedAuthority autorizacion = null;
        /*for (AppUserRole rol : appUser.getAppUserRoles()) {
            autorizacion = new SimpleGrantedAuthority(rol.name());
            autorizaciones.add(autorizacion);
        }*/
        autorizacion = new SimpleGrantedAuthority("ROLE_" + appUser.getAppUserRole());
        autorizaciones.add(autorizacion);

        return new User(
                appUser.getUsername(),
                "{noop}" + appUser.getPassword(),
                true, true, true,true,
                autorizaciones);
    }
}
