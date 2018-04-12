/**
 * EggDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.eg;

import com.hua.util.ThreadUtil;

/**
 * EggDriver
 * 描述: 
 * @author  qye.zheng
 */
public final class EggDriver
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private EggDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 模拟多线程
	 * @author  qye.zheng
	 */
	public static void simulate()
	{
		/*
		 2个独立的线程，鸡蛋生产者和鸡蛋消费者，共享
		 一个装鸡蛋的盘子
		 */
		// 竞争、公共资源
		final EggPlate eggPlate = new EggPlate();
		
		
		final Runnable eggProducer1 = new EggProducer(eggPlate);
		final Runnable eggConsumer1 = new EggConsumer(eggPlate);
		
		final Thread eggProducerThread1 = new Thread(eggProducer1, "鸡蛋生产者线程1");
		final Thread eggConsumerThread1 = new Thread(eggConsumer1, "鸡蛋消费者线程1");
		
		//eggProducerThread1.start();
		//eggConsumerThread1.start();
		
		
		final Runnable eggProducer2 = new EggProducer(eggPlate);
		final Runnable eggConsumer2 = new EggConsumer(eggPlate);
		
		final Thread eggProducerThread2 = new Thread(eggProducer2, "鸡蛋生产者线程2");
		final Thread eggConsumerThread2 = new Thread(eggConsumer2, "鸡蛋消费者线程2");
		
		
		eggProducerThread1.start();
		eggConsumerThread1.start();
		
		eggProducerThread2.start();
		eggConsumerThread2.start();
		
		final int second = 5;
		ThreadUtil.currentThreadSleep(second);
		
	}


}
