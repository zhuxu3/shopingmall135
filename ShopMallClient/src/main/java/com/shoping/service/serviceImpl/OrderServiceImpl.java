package com.shoping.service.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoping.config.RedisClient;
import com.shoping.dao.GoodsDao;
import com.shoping.dao.OrderDao;
import com.shoping.entity.Goods;
import com.shoping.entity.Order;
import com.shoping.service.GoodsService;
import com.shoping.service.OrderService;
import com.shoping.service.ThreadPoolService;
import com.shoping.util.CreateOrderNum;
import com.shoping.util.RedisUtile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	@Autowired
	GoodsService goodsService;
	@Autowired
	RedisClient redisClient;
	@Autowired
	ThreadPoolService threadPoolService;
	
	
	@Override
	@Transactional
	public synchronized String addOrder(String goodsId, int goodsSum, String account, String address) {
		String result=null;
//		JedisPool jedisPool = redisClient.getJedisPool();
//		Jedis jedis = jedisPool.getResource();
		/***************** 方法一：从redis中存放数据 ***************/
////		 1.查询redis中key值为goodsId的信息
////		 获取redis中商品列表 使用set获取购买商品的信息
//		 Set<Map<String, Goods>> goodslist=(Set<Map<String, Goods>>)
//		 RedisUtile.deserialize(jedis.get("goodslist").getBytes());
//		 //查询数据edis库中没有则去数据库进行查询
//		 if(null==goodslist){
//		 //查询数据
//		 Goods goods = new Goods();
//		 goods = goodsService.findGoodsById(goodsId);
//		 //将数据存放到redis中
//		 RedisUtile.putGoods(goods);
//		 }
////		 2.计算总金额 大于0：生成订单号，添加数据进数据库 等于0：返回库存不够提示信息
//		 int checkResult=threadPoolService.excuteCheckStock(goodslist,goodsId,goodsSum);

		/************* 方法二：从数据库中存放数据 ***************/
		boolean checkresult; // 判断是否库中有足够商品
		Goods goods = new Goods();
		goods = goodsService.findGoodsById(goodsId);
		checkresult = (Integer.parseInt(goods.getStock()) - goodsSum) >= 0;
		/*****库存足够时执行以下代码*****/
		if (checkresult) {
			try {
				Order order = new Order();
				//生成订单号
				order.setOrderId(CreateOrderNum.creatOrderNum());
				order.setAccount(account);
				order.setGoodsId(goodsId);
				BigDecimal orderPrice = new BigDecimal(goodsSum);
				order.setPrice(goods.getPrice().multiply(orderPrice)); // 计算总价格
				order.setAddress(address);
				order.setLogisticsStatus("达到配送点");
				order.setOrderStatus("配送中");
				order.setDistributorId("123");
				//分库存储数据 根据账号进行取模 例：order1，order2 （仅举例，也可以按照时间进行分区）
//				StringBuilder sb=new StringBuilder("order");
				//根据账号的hashcode进行
//				String table = sb.append(order.getAccount().hashCode()%12).toString();
//				orderDao.addOrder(order,table);
				//第一步：新增订单数据
				orderDao.addOrder(order);
				//第二步：扣除数据库中该商品的库存量
				goodsService.updateStock(goods,goodsSum);
				result="购买成功";
			} catch (Exception e) {
				result="系统繁忙，请重新下单";
				e.printStackTrace();
			}
		}
		return result; 
	}
	
	
	@Override
	public List<Order> findOrderByAccount(String account) {
//		StringBuilder sb=new StringBuilder("order");
		//根据账号的hashcode进行
//		String table = sb.append(order.getAccount().hashCode()%12).toString();
//		List<Order> orderlist=orderDao.findOrderByAccount(account，table);
		List<Order> orderlist=orderDao.findOrderByAccount(account);
		return orderlist;
	}


	

}
