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
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @type EnergySource
 * @description 电源
 * @author qianye.zheng
 */
public class EnergySource3
{
	
	/* 最大电量 */
	private final Long MAX_LEVEL = 100L;
	
	private Long level = MAX_LEVEL;
	
	private Long usage = 0L;
	
	private ReadWriteLock monitor = new ReentrantReadWriteLock();
	
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
	private EnergySource3()
	{
	}
	
	/**
	 * 
	 * @description 创建电源
	 * @return
	 * @author qianye.zheng
	 */
	public static final EnergySource3 create()
	{
		final EnergySource3 source = new EnergySource3();
		// 线程初始化
		source.init();
		
		return source;
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
	 * @description 用电
	 * @param units 用电量
	 * @return
	 * @author qianye.zheng
	 */
	public boolean useEnergy(final Long units)
	{
		// 获取写锁
		//monitor.writeLock().lock();
		try
		{
			monitor.writeLock().tryLock(10, TimeUnit.SECONDS);
			if (units > 0 && units <= level)
			{
				level -= units;
				usage++;
				
				return true;
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		} finally
		{
			// 释放写锁
			monitor.writeLock().unlock();
		}
		
		return false;
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void replenish()
	{
		// 获取写锁
		monitor.writeLock().lock();
		try
		{
			if (level < MAX_LEVEL)
			{
				level++;
			}
		} finally
		{
			// 释放写锁
			monitor.writeLock().unlock();
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
	 * @description 关闭线程池
	 * @author qianye.zheng
	 */
	public static final void shutdown()
	{
		replenishTimer.shutdown();
	}

	/**
	 * 
	 * @description 可用电量
	 * @return
	 * @author qianye.zheng
	 */
	public Long getUnitsAvailable()
	{
		// 获取读锁
		monitor.readLock().lock();
		try
		{
			return level;
		} finally
		{ 
			// 释放读锁
			monitor.readLock().unlock();
		}
	}
	
	/**
	* @return the usage
	*/
	public final Long getUsage()
	{
		// 获取读锁
		monitor.readLock().lock();
		try
		{
			return usage;
		} finally
		{
			// 释放读锁
			monitor.readLock().unlock();
		}
	}
	
}
