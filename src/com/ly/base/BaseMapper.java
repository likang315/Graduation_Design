package com.ly.base;

import java.util.List;
import java.util.Map;


/**
 * 所有的Mapper继承这个接口
 * 已经实现基本的 增,删,改,查接口,不需要重复写
 *
 * @version 1.0v
 */
public interface BaseMapper<T> extends Base<T> {

    /**
     * 返回分页后的数据
     *
     * @param map
     * @return
     */
    List<T> query(Map<String, Object> map);
}
