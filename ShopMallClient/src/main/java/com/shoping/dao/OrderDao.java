package com.shoping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shoping.entity.Order;

@Mapper
public interface OrderDao {

	public void addOrder(Order order);

	public List<Order> findOrderByAccount(@Param(value="account")String account);
}
