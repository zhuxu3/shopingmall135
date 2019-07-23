package com.shoping.service;

import java.util.List;

import com.shoping.entity.Order;

public interface OrderService {
	
	/**
	 * 根据账号查找订单信息
	 * @param account
	 * @return
	 */
	public List<Order> findOrderByAccount(String account);
	/**
	 * 持久化订单
	 * @param order
	 * @return 
	 */
	public String addOrder(String goodsId, int goodsSum, String account, String address);

}
