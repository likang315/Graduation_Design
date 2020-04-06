package com.ly.service;

import com.ly.base.BaseService;
import com.ly.entity.background.Dic;



public interface DicService extends BaseService<Dic>{
	public Dic isExist(Dic dic);
}
