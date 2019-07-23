package com.shoping.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shoping.entity.Goods;
import com.shoping.entity.Order;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 解决redis list中添加实体类的问题
 * @author zhuxu
 *
 */
public class RedisUtile {
	
	private final static Logger logger = LoggerFactory.getLogger(RedisUtile.class);
	
	private static  JedisPool jedisPool;
	
	/**
	 * 序列化
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {    
        if (value == null) {    
            throw new NullPointerException("Can't serialize null");    
        }    
        byte[] rv=null;    
        ByteArrayOutputStream bos = null;    
        ObjectOutputStream os = null;    
        try {    
            bos = new ByteArrayOutputStream();    
            os = new ObjectOutputStream(bos);    
            os.writeObject(value);    
            os.close();    
            bos.close();    
            rv = bos.toByteArray();    
        } catch (IOException e) {    
            throw new IllegalArgumentException("Non-serializable object", e);    
        } finally {    
            try {  
                 if(os!=null)os.close();  
                 if(bos!=null)bos.close();  
            }catch (Exception e2) {  
             e2.printStackTrace();  
            }    
        }    
        return rv;    
    }    
	
	/**
	 * 反序列化
	 * @param in
	 * @return
	 */
	public static Object deserialize(byte[] in) {
		Object result = null;                 //返回结果
		ByteArrayInputStream bis = null;      //输入流
		ObjectInputStream is = null;          //转化为object
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				result = is.readObject();
				is.close();
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (bis != null)
					bis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	

	/**
	 * 更新redis中的数据
	 * @param goods
	 * @return
	 */
	public static void putGoods(Goods goods) {
		
		// set Object(Seckill) -> 序列号 -> byte[]
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				//获取集合数据
				Map<String, Goods> map =new HashMap<String, Goods>();
				map.put(goods.getGoodsId(), goods);
				byte[] ma=RedisUtile.serialize(map);
				jedis.sadd("goodslist", ma.toString());
//				jedis.zadd("goodslist", RedisUtile.serialize(goods));
				// 超时缓存(不是一条一条存储的所以就不设置超时时间了。应该是一条一条存储设置超时时间)
//				int timeout = 60 * 60;
//				String result = jedis.setex(key.getBytes(), timeout, bytes);
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
