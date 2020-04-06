package com.ly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.TradeLog;
import com.ly.mapper.TradeMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.TradeService;

@Transactional
@Service("tradeService")
public class TradeServiceImpl implements TradeService {

	
	@Autowired
	private TradeMapper tradeMapper;
	
	@Override
	public PageView query(PageView pageView, TradeLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TradeLog> queryAll(TradeLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TradeLog t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public TradeLog getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TradeLog t) throws Exception {
		tradeMapper.add(t);

	}

}
