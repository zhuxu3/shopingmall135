package com.shoping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	private Logger logger=LoggerFactory.getLogger(RedisConfig.class);
	
	private final static String prefix="redis.server";

	
	
	
	@ConfigurationProperties(prefix =prefix)
	public class JedisProperties {

		private String host;

		private int port;

		private String password;

		private int maxTotal;

		private int maxIdle;

		private int maxWaitMillis;

		private int timeOut;

		
		@Bean(name = "jedisPool")
		public  JedisPool jedispool(){
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxIdle(maxIdle);
			config.setMaxTotal(maxTotal);
			config.setMaxWaitMillis(maxWaitMillis);
			return new JedisPool(config,host,port,timeOut,password);
		}
		
		@Bean
		public RedisClient redisClient(@Qualifier("jedisPool") JedisPool pool) {
			logger.info("初始化……Redis Client==Host={},Port={}", host, port);
			RedisClient redisClient = new RedisClient();
			redisClient.setJedisPool(pool);
			return redisClient;
		}
		
		public int getTimeOut() {
			return timeOut;
		}

		public void setTimeOut(int timeOut) {
			this.timeOut = timeOut;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public int getMaxTotal() {
			return maxTotal;
		}

		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}

		public int getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public int getMaxWaitMillis() {
			return maxWaitMillis;
		}

		public void setMaxWaitMillis(int maxWaitMillis) {
			this.maxWaitMillis = maxWaitMillis;
		}

	}
	
	

}
