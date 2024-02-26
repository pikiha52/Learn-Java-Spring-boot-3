package com.first_project.demo.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first_project.demo.domain.model.Users;

@Repository
public interface UserRepositories extends JpaRepository<Users, Long>  {

    Optional<Users> findByEmail(String email);

 }
