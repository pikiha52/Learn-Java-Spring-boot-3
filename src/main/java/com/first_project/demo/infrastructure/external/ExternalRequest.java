package com.first_project.demo.infrastructure.external;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExternalRequest {

    public Object requestHttp(HttpMethod httpMethod, String uri, String bodyString) {
        return null;
    }


}
