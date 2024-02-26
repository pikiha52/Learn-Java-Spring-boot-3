package com.first_project.demo.domain.ports.outbound.cache;

import java.util.concurrent.TimeUnit;

public interface SetCachePort {
    Boolean setCache(String key, TimeUnit expire, String data);
}
