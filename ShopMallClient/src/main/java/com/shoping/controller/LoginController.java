package com.shoping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoping.config.RedisClient;
import com.shoping.config.RedisConfig;
import com.shoping.entity.Goods;
import com.shoping.entity.UserCustomer;
import com.shoping.service.GoodsService;
import com.shoping.service.LoginService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Controller
public class LoginController {
	
	
	@Autowired
	LoginService loginServece;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	RedisClient redisClient;
	
	/**
	 * 跳转到登陆页面
	 * @param mv
	 * @return
	 */
	@GetMapping(value="login")
	public ModelAndView login(){
		ModelAndView mv =new ModelAndView("Login");
	    return mv;
	}

	/**
	 * 登陆功能
	 * 1.验证账号密码
	 * 2.验证账号是否已经登陆
	 * 3.将账号添加到session中
	 * @param requst
	 * @param response
	 * @return
	 */
	@RequestMapping(value="regist")
	public ModelAndView regist(HttpServletRequest requst,HttpServletResponse response){
//		RedisTemplate redisTemplate=new RedisTemplate<>();
		ModelAndView mv;
		UserCustomer user=new UserCustomer();
		//获取账号密码进行验证  1.账号密码正确返回true  2.账号密码不争取返回false
		user.setAccount(requst.getParameter("account"));
		user.setPassword(requst.getParameter("password"));
		boolean result = loginServece.longin(user);
		//账号密码正确则判断账号是否已经登陆
		if(result){
			requst.getSession().removeAttribute(user.getAccount());
			//获取session中的账号  1.如果session没有账号则将account添加进session跳转到商品页面   2.如果session中有账号则提示该账号已经登陆
 			if(null==requst.getSession().getAttribute(user.getAccount())){
 				List<Goods> goodslist= new ArrayList<Goods>();
 				//将账号添加进session不让其他人登陆 登出时候需要删除session
 				requst.getSession().setAttribute(user.getAccount(), "login");
 				//返回页面
 				mv=new ModelAndView("SerchOder");
 				//查询所有商品
 				Goods goods = new Goods();
 				goodslist = goodsService.findGoods(goods);
 				/**
 				 * 方法二：通过redis获取商品数据
 				 * redis中需要有商品数据
 				 */
 				//获取连接池的配置对象
// 				JedisPool jedisPool = redisClient.getJedisPool();
// 				Jedis jedis=jedisPool.getResource();
// 				//获取redis中存储的货物列表
// 				byte[] goodsByte=jedis.get("goodslist".getBytes());
// 				List<Goods> goodsList=(List<Goods>) redisTemplate.opsForList().range("goodsList",0, -1);
 				//将商品列表添加到model中
// 				for(int i=0;i<goodslist.size();i++){
// 					
// 				}
 				mv.addObject("goods",goodslist);
 				mv.addObject("customer",user.getAccount());
			}else{
				mv= new ModelAndView("error");
				mv.addObject("message", "该账号已经登陆");
			}
			
		}else{
			mv= new ModelAndView("error");
			mv.addObject("message", "账号密码错误");
		}
		return mv;
	}
}
