package com.first_project.demo.domain.ports.outbound.user;

import java.util.Optional;

import com.first_project.demo.domain.model.UsersLdap;

public interface ShowUserLdapPort {
    Optional<UsersLdap> findByUidLdap(String uid);
}
