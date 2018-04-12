/**
  * @filename EnergySource.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @type EnergySource
 * @description 电源
 * @author qianye.zheng
 */
public class EnergySource2
{
	
	/* 最大电量 */
	private final Long MAX_LEVEL = 100L;
	
	/*
	 * AtomicLong自身可以保证在多线程访问下的数据安全
	 */
	private AtomicLong level = new AtomicLong(MAX_LEVEL);
	
	/*
	 * 多个对象共享一个线程池
	 */
	/* 充电任务定时器 */
	private static final ScheduledExecutorService replenishTimer = Executors.newScheduledThreadPool(10, new ThreadFactory ()
	{
		/**
		 * @description 
		 * @param r
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public Thread newThread(Runnable r)
		{
			/*
			 * 设置为守护线程
			 * 这样就可以不用手工调用shutdown 去关闭线程池
			 */
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			
			return thread;
		}
	});
	
	private ScheduledFuture<?> replenishTask;
	
	/**
	 * 
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	private EnergySource2()
	{
	}
	
	/**
	 * 
	 * @description 用电
	 * @param units 用电量
	 * @return
	 * @author qianye.zheng
	 */
	public boolean useEnergy(final Long units)
	{
		final Long currentLevel = level.get();
		if (units > 0 && units <= currentLevel)
		{
			
			return level.compareAndSet(currentLevel, currentLevel - units);
		}
		
		return false;
	}
	
	/**
	 * 
	 * @description 可用电量
	 * @return
	 * @author qianye.zheng
	 */
	public Long getUnitsAvailable()
	{
		return level.get();
	}

	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	private void init()
	{
		replenishTask = replenishTimer.scheduleAtFixedRate(new Runnable()
		{
			// 设定充电周期
			/*
			 * scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
			 */
			/**
			 * @description 
			 * @author qianye.zheng
			 */
			@Override
			public void run()
			{
				replenish();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void replenish()
	{
		if (level.get() < MAX_LEVEL)
		{
			level.incrementAndGet();
		}
	}
	
	/**
	 * 
	 * @description 关闭电源
	 * @author qianye.zheng
	 */
	public synchronized void stopEnergySource()
	{
		replenishTask.cancel(false);
	}
	
	/**
	 * 
	 * @description 创建电源
	 * @return
	 * @author qianye.zheng
	 */
	public static final EnergySource2 create()
	{
		final EnergySource2 source = new EnergySource2();
		// 线程初始化
		source.init();
		
		return source;
	}
	
	/**
	 * 
	 * @description 关闭线程池
	 * @author qianye.zheng
	 */
	public static final void shutdown()
	{
		replenishTimer.shutdown();
	}
}
