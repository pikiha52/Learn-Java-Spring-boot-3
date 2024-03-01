package com.first_project.demo.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.first_project.demo.domain.model.UsersLdap;

@Repository
public interface UserLdapRepositories extends LdapRepository<UsersLdap> {

    Optional<UsersLdap> findByUid(String uid);

 }
