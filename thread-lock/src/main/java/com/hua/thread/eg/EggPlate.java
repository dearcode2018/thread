/**
 * EggPlate.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.eg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * EggPlate 描述: 装鸡蛋盘子
 * 扮演 公共资源角色.
 * @author qye.zheng
 */
public final class EggPlate
{
	/*
	 由于实例方法增加了 同步控制(排队)，因此任意时刻只能有一个
	 线程在操作盘子，是放鸡蛋或者是取鸡蛋，满足资源限制条件之后，
	 就会让当前线程进入wati状态，同时若一个线程的动作完成，则相应
	 nofity任何处于阻塞状态的线程
	 
	 wait之后被唤醒并且成功获取synchronized控制权的线程，进行一个
	 现场恢复，继续朝着上一次wait下面的代码继续执行，因此多个线程
	 控制下的同步方法或块，应该双方沟通彼此的控制条件，而不是单独
	 由一个线程来维护自己的条件，而应该都进行维护.
	 
	 
	 
	 */

	/* 最小限制，<= 这个限制则不能再 取 鸡蛋 */
	private static final int MIN_LIMIT = 0;
	
	/* 最大限制，>= 这个限制则不能再 放 鸡蛋 */
	private static final int MAX_LIMIT = 5;
	
	private List<Object> eggs = new ArrayList<Object>(MAX_LIMIT);
	
	// 盘子当前存放的鸡蛋个数
	private int size;
	
	// 标志- 空盘
	private boolean empty = true;
	
	// 标志- 满盘
	private boolean full = false;

	// ReentrantLock 看重入的锁
	private final Lock lock = new ReentrantLock(true);
	
	/**
	 * Condition 用于线程之间的通信，保持锁的通畅
	 */
	private final Condition putCondition = lock.newCondition();
	
	private final Condition getCondition = lock.newCondition();
	
	/**
	 * 
	 * 描述: 放鸡蛋
	 * @author  qye.zheng
	 * @param egg
	 */
	public void putEgg(Object egg)
	{
		// 锁定
		lock.lock();
		try {
			// 在锁的保护下，执行独占任务
			size = eggs.size();
			if (size >= MAX_LIMIT)
			{
				// 盘已满
				full = true;
				// 一定不空
				empty = false;
			} else 
			{
				// 盘未满
				full = false;
			}
			while (full)
			{
				try
				{
					System.out.println("盘子已满，" + Thread.currentThread().getName() + "  等待");
					System.out.println();
					// 当前线程等待，直到其他线程调用此对象的notify或notifyAll方法，才被唤醒进入就绪
					/**
					 * 曾经在此阻塞的线程 被唤醒之后进入就绪 并且获取运行机会，
					 * 则将沿着wait方法之后的路径继续运行
					 */
					//this.wait();
					putCondition.await();
					System.out.println("现场恢复，" + Thread.currentThread().getName() + " 继续放鸡蛋");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			// 放鸡蛋
			eggs.add(egg);
			System.out.println("放鸡蛋后盘子鸡蛋个数 : " + eggs.size());
			System.out.println(Thread.currentThread().getName() +  " 放入鸡蛋，唤醒阻塞队列的某线程");
			System.out.println();
			// 放入一个鸡蛋，盘一定不空
			empty = false;
			
			// 唤醒阻塞队列的某线程，使之进入就绪队列
			//this.notify();
			putCondition.signal();
		} finally
		{
			// 释放锁
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * 描述: 取鸡蛋
	 * @author  qye.zheng
	 * @return
	 */
	public Object getEgg()
	{
		// 锁定
		lock.lock();
		Object egg = null;
		try {
			// 在锁的保护下，执行独占任务
			size = eggs.size();
			if (size <= MIN_LIMIT)
			{
				// 盘已空
				empty = true;
				// 一定不满
				full = false;
			} else 
			{
				// 盘未空
				empty = false;
			}
			while (empty)
			{
				try
				{
					System.out.println("盘子为空，" + Thread.currentThread().getName() + " 等待");
					System.out.println();
					// 当前线程等待，直到其他线程调用此对象的notify或notifyAll方法，才被唤醒进入就绪
					/**
					 * 曾经在此阻塞的线程 被唤醒之后进入就绪 并且获取运行机会，
					 * 则将沿着wait方法之后的路径继续运行
					 */
					//this.wait();
					getCondition.await();
					System.out.println("现场恢复，" + Thread.currentThread().getName() + " 继续取鸡蛋");
					//System.out.println("现场恢复，继续干活");
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			// 取鸡蛋
			egg = eggs.get(0);
			// 清除掉取出的鸡蛋
			eggs.remove(egg);
			System.out.println("取鸡蛋后盘子鸡蛋个数 : " + eggs.size());
			System.out.println(Thread.currentThread().getName() + " 拿到鸡蛋，唤醒阻塞队列的某线程");
			System.out.println();
			/**
			 * 唤醒在此同步监视器上等待的单个线程，如果有多个线程在此同步监视器上等待，
			 * 则唤醒其中一个，取决于CPU调度算法.
			 */
			// 唤醒阻塞队列的某线程，使之进入就绪队列
			//this.notify();
			getCondition.signal();
			// 取出一个鸡蛋， 盘一定未满
			full = false;
		} finally
		{
			// 释放锁
			lock.unlock();
		}
		
		return egg;
	}

}
