package com.first_project.demo.domain.model;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entry(base = "ou=users", objectClasses = { "inetOrgPerson", "posixAccount", "top" })
public class UsersLdap {

    @Id
    @JsonIgnore
    private Name dn;

    @DnAttribute(value = "uid")
    @JsonProperty("uid")
    private String uid;

    @Attribute(name = "cn")
    @JsonProperty("cn")
    private String cn;

    @Attribute(name = "givenname")
    @JsonProperty("firstName")
    private String firstName;

    @Attribute(name = "sn")
    @JsonProperty("lastName")
    private String lastName;

    @Attribute(name = "mail")
    @JsonProperty("email")
    private String email;
}
