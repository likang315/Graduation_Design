package com.ly.base;

import java.util.List;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-05 10:05
 */
public interface Base<T> {
    /**
     * 返回所有数据
     *
     * @param t
     * @return
     */
    List<T> queryAll(T t);

    void delete(String id) throws Exception;

    void update(T t) throws Exception;

    T getById(String id);

    void add(T t) throws Exception;
}
