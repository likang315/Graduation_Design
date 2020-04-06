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
    public List<T> queryAll(T t);

    public void delete(String id) throws Exception;

    public void update(T t) throws Exception;

    public T getById(String id);

    public void add(T t) throws Exception;
}
