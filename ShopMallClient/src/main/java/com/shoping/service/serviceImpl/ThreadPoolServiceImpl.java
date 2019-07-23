package com.shoping.service.serviceImpl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.shoping.entity.Goods;
import com.shoping.service.ThreadPoolService;

@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

	@Async("taskExcutor")
	@Override
	public int excuteCheckStock(Set<Map<String, Goods>> goodslist, String goodsId, int goodsSum) {
		try {
			int i=0;
			for(Iterator it = goodslist.iterator(); it.hasNext();i++)
			{
			    Map<String, Goods> map = (Map<String, Goods>)it.next();
			    Goods temp=map.get(goodsId);
			    int DBgoodsnum=Integer.parseInt(temp.getGoodsNum());
			    BigDecimal price;
			    if(DBgoodsnum>=goodsSum){
			    	price=(temp.getPrice());
			    	return price.intValue();
			    }else{
			    	return 0;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
