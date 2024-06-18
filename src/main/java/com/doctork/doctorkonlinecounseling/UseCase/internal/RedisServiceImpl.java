package com.doctork.doctorkonlinecounseling.UseCase.internal;

import com.doctork.doctorkonlinecounseling.boundary.internal.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate <String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String storeObject(Object object) {
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key, object);
        redisTemplate.expire(key, Duration.ofMinutes(1));
        return key;
    }

    @Override
    public <T> T getObject(String key, Class<T> type) {
        Object data = redisTemplate.opsForValue().get(key);
        return type.cast(data);
    }
}
