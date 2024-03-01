package com.first_project.demo.infrastructure.databases.ldap;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.model.UsersLdap;
import com.first_project.demo.domain.ports.outbound.user.ShowUserLdapPort;
import com.first_project.demo.infrastructure.repositories.UserLdapRepositories;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindByUidAdapter implements ShowUserLdapPort {

    private final UserLdapRepositories userLdapRepositories;

    @Override
    public Optional<UsersLdap> findByUidLdap(String uid) {
        return userLdapRepositories.findByUid(uid);
    }
    
}
