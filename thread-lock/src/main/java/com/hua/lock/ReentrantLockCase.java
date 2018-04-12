/**
  * @filename ReentrantLockCase.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hua.util.ThreadUtil;

 /**
 * @type ReentrantLockCase
 * @description 可重入锁例子
 * @author qianye.zheng
 */
public class ReentrantLockCase
{
	/* 公平的分配锁 */
	private boolean fair = true;
	
	/* 可重入锁 */
	private Lock lock;
	
	//	private double value = 0;
	private double value = 0D;
	
	/* 值-增加次数 */
	private int addTimes = 0;
	
	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public ReentrantLockCase()
	{
		this(true);
	}
	
	/**
	 * @description 构造方法
	 * @param fair
	 * @author qianye.zheng
	 */
	public ReentrantLockCase(boolean fair)
	{
		super();
		this.fair = fair;
		lock = new ReentrantLock(fair);
	}

	/**
	 * 
	 * @description 写value的值，必须在一个事务中实现，因此该方法
	 * 必须加锁
	 * @param value
	 * @author qianye.zheng
	 */
	public void writeValue(final double value)
	{
		try
		{
			// 获得锁
			lock.lock();
			System.out.println("writeValue = " + value + ", ts = " + System.currentTimeMillis());
			ThreadUtil.currentThreadSleep(2);
			this.value += value;
			addTimes++;
		} finally
		{
			// 释放锁
			lock.unlock();
		}
	}

	/**
	 * @return the value
	 */
	public final double readValue()
	{
		return value;
	}

	/**
	 * @return the addTimes
	 */
	public final int getAddTimes()
	{
		return addTimes;
	}

	/**
	 * @return the fair
	 */
	public final boolean isFair()
	{
		return fair;
	}
	
}
