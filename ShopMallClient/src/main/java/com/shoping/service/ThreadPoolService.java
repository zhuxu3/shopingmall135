package com.shoping.service;

import java.util.Map;
import java.util.Set;

import com.shoping.entity.Goods;

public interface ThreadPoolService {
	
	/**
	 * 返回物品总金额
	 * 0.无库存  其他数字：总金额
	 * @param goodslist
	 * @param goodsId
	 * @param goodsSum
	 */
	public int excuteCheckStock(Set<Map<String, Goods>> goodslist, String goodsId, int goodsSum);

}
