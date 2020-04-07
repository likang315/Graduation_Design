package com.ly.base;

import com.ly.pulgin.mybatis.plugin.PageView;


/**
 * 所有服务接口都要继承这个
 *
 * @param <T>
 */
public interface BaseService<T> extends Base<T> {
    /**
     * 返回分页后的数据
     *
     * @param pageView
     * @param t
     * @return
     */
    PageView query(PageView pageView, T t);
}
