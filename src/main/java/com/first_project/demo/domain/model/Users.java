package com.first_project.demo.domain.model;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullName", length = 55)
    private String fullName;

    @Column(name = "email", length = 55, unique = true)
    private String email;

    @Column(name = "phoneNumber", length = 22, unique = true)
    private String phoneNumber;

    @Column(name = "password", length = 201)
    private String password;

    @Column(name = "roles", length = 55)
    private String roles;

    @Column(name = "username", length = 201)
    private String username;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

}
