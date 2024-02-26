package com.first_project.demo.application.response;


import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateUserResponse {
    
    private Long id;
    private Date createdAt;

}
