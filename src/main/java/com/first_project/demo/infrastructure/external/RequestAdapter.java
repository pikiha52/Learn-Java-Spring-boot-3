package com.first_project.demo.infrastructure.external;

import org.springframework.stereotype.Component;

import com.first_project.demo.domain.ports.outbound.external.ExternalRequestPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestAdapter implements ExternalRequestPort {
    
    @Override
    public Object requestExternal() {
        throw new UnsupportedOperationException("Unimplemented method 'requestExternal'");
    }
    
}
