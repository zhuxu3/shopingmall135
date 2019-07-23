package com.shoping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadpoolConfig {
	@Bean
	public TaskExecutor taskExcutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(30);                        //核心线程数
		
		executor.setMaxPoolSize(60);                         //最大显成熟
		
		executor.setQueueCapacity(100000);                   //设置队列容量
		
		executor.setKeepAliveSeconds(300);                   //线程活跃时间
		
		executor.setWaitForTasksToCompleteOnShutdown(true);  //是否执行完任务后在关闭线程池
		return executor;
	}
}
