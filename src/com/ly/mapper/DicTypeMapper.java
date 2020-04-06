package com.ly.mapper;


import com.ly.base.BaseMapper;
import com.ly.entity.background.DicType;

public interface DicTypeMapper extends BaseMapper<DicType>{
	public DicType isExist(DicType dicType);
	public DicType queryById(DicType dicType);
}
