/**
  * @filename WriteService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.thread;

 /**
 * @type WriteService
 * @description 
 * @author qianye.zheng
 */
public class WriteService
{
	
	private String lock = "lock_";
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @author qianye.zheng
	 */
	public synchronized void write1(final String content)
	{
		/*
		 * 在方法签名上加synchronized关键字
		 */
		System.out.println("Current ThreadId1: " + Thread.currentThread().getId());
		System.out.println("Current ThreadName1: " + Thread.currentThread().getName());
/*		try
		{
			lock.wait();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}*/
		System.out.println("write1: " + content);
	}
	
	/**
	 * 
	 * @description 
	 * @param content
	 * @author qianye.zheng
	 */
	public void write2(final String content)
	{
		System.out.println("Current ThreadId2: " + Thread.currentThread().getId());
		System.out.println("Current ThreadName2: " + Thread.currentThread().getName());
		synchronized (lock)
		{
			System.out.println("get lock...");
			String a = "a";
			//a.notify();
		try
			{
				/*
				 * 
				 * 1.没有注释代码，在	System.out.println("write1: " + content); 打断点
				 * 观察，发现第二个线程可以进入 get lock，是因为第一个线程执行wait释放了锁
				 * 
				 * 2.注释掉 wait，没有释放锁，在	System.out.println("write1: " + content); 打断点
				 * 发现第二个线程没法进入get lock，是因为第一个线程没有释放锁
				 * 
				 * 3.
				 * 
				 */
				// 释放锁，进入等待状态
				lock.wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println("write1: " + content);
		}
	}
	
}
