package com.first_project.demo.infrastructure.databases.ldap;

import java.util.List;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.UsersLdap;
import com.first_project.demo.domain.ports.outbound.user.UserLdapsPort;
import com.first_project.demo.infrastructure.repositories.UserLdapRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AllUserLdapAdapter implements UserLdapsPort {

    private final UserLdapRepositories userLdapRepositories;

    @Override
    public List<UsersLdap> allUserLdaps() {
        return userLdapRepositories.findAll();
    }
    
}
