package com.ly.service;

import com.ly.base.BaseService;
import com.ly.entity.background.DicType;



public interface DicTypeService extends BaseService<DicType>{
	public DicType isExist(DicType dicType);
	public DicType queryById(DicType dicType);
}
