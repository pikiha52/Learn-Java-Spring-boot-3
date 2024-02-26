package com.first_project.demo.infrastructure.cache.adapter;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import com.first_project.demo.domain.ports.outbound.cache.SetCachePort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetCacheAdapter implements SetCachePort {


    @Override
    public Boolean setCache(String key, TimeUnit expire, String data) {
        throw new UnsupportedOperationException("Unimplemented method 'setCache'");
    }
    
}
