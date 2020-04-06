package com.ly.mapper;


import com.ly.base.BaseMapper;
import com.ly.entity.background.Dic;

public interface DicMapper extends BaseMapper<Dic>{
	public Dic isExist(Dic dic);
}
