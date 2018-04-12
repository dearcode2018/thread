/**
  * @filename ReentrantReadWriteLockCase.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.hua.util.ThreadUtil;

 /**
 * @type ReentrantReadWriteLockCase
 * @description 可重入读写锁例子
 * @author qianye.zheng
 */
public class ReentrantReadWriteLockCase
{
	
	/**
	 * ReadWriteLock内置2个Lock，读、写
	 * 
	 * 读写锁:
	 * 读锁: 多个线程可以并发获得，共享锁
	 * 写锁: 只有一个线程可以获得，排他锁
	 * 
	 * 读的时候可以并发读，但不能写，写的时候既不能并发写也不能读.
	 * 
	 * ReadWriteLock 和适合处理类似于文件的读写操作.
	 */
	
	/* 公平的分配锁 */
	private boolean fair = true;
	
	/* 可重入读写锁 */
	private ReadWriteLock lock;
	
	//	private double value = 0;
	private double value = 0D;
	
	/* 值-增加次数 */
	private int addTimes = 0;
	
	/**
	 * @description 构造方法
	 * @author qianye.zheng
	 */
	public ReentrantReadWriteLockCase()
	{
		this(true);
	}

	/**
	 * @description 构造方法
	 * @param fair
	 * @author qianye.zheng
	 */
	public ReentrantReadWriteLockCase(boolean fair)
	{
		super();
		this.fair = fair;
		 lock = new ReentrantReadWriteLock();
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
		// 获取写锁
		Lock writeLock = lock.writeLock();
		try
		{
			writeLock.lock();
			System.out.println("writeValue = " + value + ", ts = " + System.currentTimeMillis());
			ThreadUtil.currentThreadSleep(2);
			this.value += value;
			addTimes++;
		} finally
		{
			// 释放锁
			writeLock.unlock();
		}
	}

	/**
	 * @return the value
	 */
	public final double readValue()
	{
		// 获取读锁
		Lock readLock = lock.readLock();
		
		try
		{
			readLock.lock();
			System.out.println("readValue ts = " + System.currentTimeMillis());
			ThreadUtil.currentThreadSleep(1);
			
			// 读
			return value;
		} finally
		{
			// 释放锁
			readLock.unlock();
		}
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
