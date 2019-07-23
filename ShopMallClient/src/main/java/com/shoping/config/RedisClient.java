package com.shoping.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * redis服务
 * 配置连接池（默认10000）
 * @author zhuxu
 *
 */
public class RedisClient {
	private JedisPool pool;
	
	public synchronized  void set(String key,Object value){
		Jedis jedis=null;
		try{
			jedis=pool.getResource();
			jedis.set(key, value.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		
		
	}
	
	
	/**
	 * 设置过期时间 
	 * @param key
	 * @param value
	 * @param exptime
	 * @throws Exception
	 */
	public void setWithExpireTime(String key, String value, int exptime) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value, "NX", "EX", 300);
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			jedis.close();
		}
	}

	
	public String get(String key) {

		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			if (jedis != null)
			jedis.close();
		}
		return null;
	}
	public JedisPool getJedisPool() {
		return pool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.pool = jedisPool;
	}


}
