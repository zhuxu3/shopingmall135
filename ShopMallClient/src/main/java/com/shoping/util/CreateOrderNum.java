package com.shoping.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.RandomAccess;

/**
 * 生成唯一订单号 生成4位随机数
 * 
 * @author zhuxu
 *
 */
public class CreateOrderNum {
	public static synchronized String creatOrderNum() throws UnknownHostException {
		StringBuilder orderNum = new StringBuilder(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Random random = new Random();
		orderNum.append(random.nextInt(9999));
		return orderNum.toString();
	}

}
