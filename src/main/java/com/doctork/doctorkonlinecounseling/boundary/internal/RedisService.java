package com.doctork.doctorkonlinecounseling.boundary.internal;

public interface RedisService {

    String storeObject(Object object);

    <T> T getObject(String key, Class<T> type);


}
