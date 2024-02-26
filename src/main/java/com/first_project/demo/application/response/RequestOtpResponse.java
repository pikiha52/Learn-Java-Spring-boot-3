package com.first_project.demo.application.response;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestOtpResponse {
    
    public Boolean isSend;
    public Date createdOtp;

}
