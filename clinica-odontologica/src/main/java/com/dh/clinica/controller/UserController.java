package com.dh.clinica.controller;

import com.dh.clinica.entity.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/user")
    public ResponseEntity<?> user() throws Exception {

        UserDetails userDatails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser user = new AppUser();
        user.setName(userDatails.getUsername());

        return ResponseEntity.ok(user);
    }
}
