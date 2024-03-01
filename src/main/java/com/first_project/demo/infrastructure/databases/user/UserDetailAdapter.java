package com.first_project.demo.infrastructure.databases.user;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.Users;
import com.first_project.demo.domain.ports.outbound.user.UserDetailsPort;
import com.first_project.demo.infrastructure.filter.UserInfoDetails;
import com.first_project.demo.infrastructure.repositories.UserRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailAdapter implements UserDetailsPort {
    private final UserRepositories userRepositories;
    
    @Override
    public UserDetails userDetailByEmailPort(String email) {
        Optional<Users> user = userRepositories.findByEmail(email);
        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }
    
}
