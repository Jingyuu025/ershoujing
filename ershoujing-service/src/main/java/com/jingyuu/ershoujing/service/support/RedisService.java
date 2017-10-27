package com.jingyuu.ershoujing.service.support;

/**
 * @author owen
 * @date 2017-10-26
 */
public interface RedisService<T> {

    /**
     * put
     *
     * @param key    key
     * @param domain 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    void put(String key, T domain, long expire);

    /**
     * put
     *
     * @param key    key
     * @param domain 对象
     */
    void put(String key, T domain);

    /**
     * set
     *
     * @param key
     * @param field
     * @param value
     */
    void hset(String key, String field, T value);

    /**
     *
     * @param key
     * @return
     */
    T get(String key);

    /**
     *
     * @param key
     * @param filed
     * @return
     */
    T hget(String key, String filed);


}
