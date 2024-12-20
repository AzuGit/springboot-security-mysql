package com.securityApp.service;

import com.securityApp.persistence.entity.UserEntity;

import com.securityApp.persistence.entity.UserSecurity;
import com.securityApp.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));

        Set<SimpleGrantedAuthority> authoritylist = new HashSet<>();
        userEntity.getRoles().stream()
                .forEach(rol -> authoritylist.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authoritylist.add(new SimpleGrantedAuthority(permission.getName())));


            UserSecurity user = new UserSecurity(userEntity.getId(),userEntity.getUsername(), userEntity.getPassword(), userEntity, authoritylist);


            return user;



    }
}
