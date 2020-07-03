/**
  * @filename ThreadPoolConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @type ThreadPoolConfiguration
 * @description 线程池 - 自定义或修饰自动配置
 * 自动配置:
 * org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
 * org.springframework.boot.autoconfigure.task.TaskExecutionProperties
 * @author qianye.zheng
 */
@Configuration
@EnableConfigurationProperties(TaskExecutionProperties.class)
public class ThreadPoolConfiguration {
	
	
	/**
	 * ThreadPoolTaskExecutor 构建方式: 
	 * 1.全自动: 全部使用配置驱动即可，spring.task.execution.xx，有些是机制是不支持配置的，但全自动可以满足绝大多数功能
	 * 
	 * 2.半自动，使用自动配置，加上自定义配置和修饰: TaskExecutorCustomizer/TaskDecorator/ThreadFactory/RejectedExecutionHandler
	 * 通过ThreadPoolTaskExecutor.initializeExecutor 将 ThreadFactory/RejectedExecutionHandler 注入
	 * 
	 * 
	 * 3.全手动构建ThreadPoolTaskExecutor，配置可以使用 TaskExecutionProperties，类上标注@EnableConfigurationProperties(TaskExecutionProperties.class)
	 * 
	 * 注意: 全手动 和 全自动/半自动不能共存，半自动和全自动可以共存
	 * 
	 * 验证全自动/半自动 需要把 全手动代码注释掉，然后再运行 
	 * 
	 * 
	 */
	
	/* =============== 1.全自动，没有代码，全通过配置来生效 =============== */
	
	
	/* =============== 2.半自动 =============== */
	
	/**
	 * 说明: 发现注入Bean的方式不行，要通过其他方式才可以.
	 * 因为 org.springframework.scheduling.concurrent.ExecutorConfigurationSupport.initialize() 初始构造了默认值
	 * @description 自定义拒绝执行策略
	 * @return
	 * @author qianye.zheng
	 */
	/*
	 * @Bean public RejectedExecutionHandler rejectedExecutionHandler() { return
	 * new ThreadPoolExecutor.DiscardPolicy(); }
	 */
	/**
	 * 
	 * @description 自定义配置
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public TaskExecutorCustomizer customizer() {
		return x -> {
			x.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		};
	}
	
	/**
	 * 
	 * @description 修饰器
	 * 获取Runnable
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public TaskDecorator decorator() {
		return x -> {
			System.out.println("decorator");
			/*
			 * 如果在此执行了，则后面不再重复执行
			 * 
			 * 若返回新的Runnable，则会继续执行
			 * 若返回原来的Runnable，则会判断是否已经执行了再执行，避免重复执行
			 */
			x.run();
			
			// 返回原来的
			//return x;
			
			// 返回新的
			return () -> {System.out.println("去做其他事情了..");};
		};
	}
	
	/* =============== 3.全手动 (加上 @EnableConfigurationProperties(TaskExecutionProperties.class) ) =============== */
	
	/**
	 * 
	 * @description 标注 @EnableConfigurationProperties(TaskExecutionProperties.class)
	 * 手动构造 线程池执行器对象
	 * @param properties
	 * @return
	 * @author qianye.zheng
	 */
	/*
	@Bean
	public ThreadPoolTaskExecutor taskExecutor(final TaskExecutionProperties properties) {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix(properties.getThreadNamePrefix());
		// 拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		//executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		
		// 池配置
		final TaskExecutionProperties.Pool pool = properties.getPool();
		executor.setAllowCoreThreadTimeOut(pool.isAllowCoreThreadTimeout());
		executor.setCorePoolSize(pool.getCoreSize());
		executor.setMaxPoolSize(pool.getMaxSize());
		executor.setKeepAliveSeconds((int) pool.getKeepAlive().toMillis() / 1000);
		executor.setQueueCapacity(pool.getQueueCapacity());
		// 关闭参数
		executor.setAwaitTerminationMillis(properties.getShutdown().getAwaitTerminationPeriod().toMillis());
		
		return executor;
	} 
*/
	
	
}
