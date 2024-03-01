package com.first_project.demo.domain.ports.outbound.user;

import java.util.List;

import com.first_project.demo.domain.model.UsersLdap;

public interface UserLdapsPort {
    List<UsersLdap> allUserLdaps();
}
