package com.shoping.controller;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.config.RedisClient;
import com.shoping.entity.Goods;
import com.shoping.entity.Order;
import com.shoping.service.GoodsService;
import com.shoping.service.OrderService;
import com.shoping.service.ThreadPoolService;
import com.shoping.util.CreateOrderNum;
import com.shoping.util.RedisUtile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class OrderController {

	public static Log log = LogFactory.getLog(OrderController.class);
	@Autowired
	GoodsService goodsService;
	@Autowired
	OrderService orderService;
	@Autowired
	ThreadPoolService threadPoolService;
	@Autowired
	RedisClient redisClient;

	/**
	 * 添加到购物车中
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/shopping")
	public ModelAndView shoppingsub(HttpServletRequest re) {
		ModelAndView mv = new ModelAndView();
		// 只传了一个值，如果是list用for循环添加到list中即可
		String goodsId = re.getParameter("addGoodsId"); // 购买商品编号
		int goodsSum = Integer.parseInt(re.getParameter("addGoodsSum")); // 购买商品的总量
		String account = re.getParameter("account"); // 购买者账号
		String address = re.getParameter("address"); // 送货地址
		/*****如果使用分布式锁则去除syn*****/
		String result=orderService.addOrder(goodsId,goodsSum,account,address);
//		JedisPool jedisPool = redisClient.getJedisPool();
//		Jedijedis = jedisPool.getResource();
//
//		// 使用分布式锁控制控制多用物访问同一物品的并发问题
//		// 获取redis分布式锁  （使用分布式锁就不用syc锁）
//		String lock = jedis.get("checkStockLock");
//		// 如果没有获取到则进行加锁并执行新增订单，扣除库存操作
//		if (null == lock) {
//			try{
//				//进行加锁
//				//获取锁，若已经贝加锁则不做任何操作，超时时间为1秒
//				jedis.set("checkStockLock", "lock", "NX", "PX", 1000);
//				//设置订单信息
//				String result=orderService.addOrder(goodsId,goodsSum,account,address);
//			}catch (Exception e) {
//				log.info("新增订单异常");
//			}finally {
//				//释放锁
//				jedis.del("checkStockLock");
//			}
//		}
		mv.addObject("message", result);
		mv.setViewName("error");
		return mv;

	}

	/**
	 * 查询用户的所有订单信息
	 * 
	 * @return
	 */
	@PostMapping("/OrderDetile")
	public ModelAndView OrderDetile(HttpServletRequest requset) {
		ModelAndView mv = new ModelAndView("OrderDetail");
		// 获取session中的账号查询所有订单
		String account = requset.getParameter("customer");
		System.out.println(account);
		List<Order> orderDetile = orderService.findOrderByAccount(account);
		mv.addObject("detile", orderDetile);
		System.out.println(orderDetile.size());
		return mv;
	}
}
