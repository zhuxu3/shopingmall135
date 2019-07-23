package com.shoping.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoping.dao.GoodsDao;
import com.shoping.entity.Goods;
import com.shoping.service.GoodsService;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsDao goodsDao;
	
	@Override
	public List<Goods> findGoods(Goods goods) {
		List<Goods> result=goodsDao.findGoods();
		return result;
	}

	@Override
	public synchronized Goods findGoodsById(String id) {
		Goods result=goodsDao.findGoodsById(id);
		return result;
	}

	@Override
	public void updateStock(Goods gooGoodsds,int goodsSum) {
		System.out.println(String.valueOf(Integer.parseInt(gooGoodsds.getStock())-goodsSum));
		System.out.println(gooGoodsds.getGoodsId());
		goodsDao.updateStock(String.valueOf(Integer.parseInt(gooGoodsds.getStock())-goodsSum),gooGoodsds.getGoodsId());
	}

}
