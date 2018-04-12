/**
  * @filename TotalFileSizeConcurrentWLatch.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @type TotalFileSizeConcurrentWLatch
 * @description 
 * @author qianye.zheng
 */
public class TotalFileSizeConcurrentWLatch
{
	
	
	/**
	 * 如果只是想在2个线程之前交换数据，可以使用 Exchanger，Exchanger可看作一个同步点，2个线程在该同步点
	 * 能够以线程安全的方式交换数据. 如果2个线程运行的速度不同，则运行较快的线程将会被阻塞，知道运行较慢的
	 * 线程赶到同步点后，2个线程才开始交换数据.
	 * 
	 * 如果想要在线程间互发多组数据，则 BlockingQueue 接口可以派上用场. 该接口的特点是，如果队列里没有可用
	 * 空间，则插入操作将会被阻塞，而如果队列里没有可用数据，则删除操作将被阻塞.
	 * 若想要使插入/删除操作一一对应，可以使用 SynchronousQueue.该类的作用是将本线程的每个插入操作和其他线程
	 * 相应的删除操作相匹配，以完成类似于手递手形式的数据传输.
	 * 
	 * 如果希望数据可以根据某种优先级在队列中上下浮动，则可以使用 PriorityBlockingQueue.
	 * 链表实现的阻塞队列: LinkedBlockingQueue
	 * 数组实现的阻塞队列: ArrayBlockingQueue
	 * 
	 */
	
	private ExecutorService executorService;
	
	/* 新增要处理的目录 */
	private final AtomicLong pendingFileVisits = new AtomicLong();
	
	/* 文件总大小 */
	private final AtomicLong totalSize = new AtomicLong();
	
	/* 线程闩 开关
	 * count值大一些可以让多个线程同时处于等待其释放状态
	 * 
	 * 如果希望多线程在继续执行其他任务之前都能抵达同一个协作位置，就可以采用这种方式实现
	 * 
	 * CountDownLatch是不可复用的，所以一旦某个线程闩的实例在同步动作中被用过了之后，就必须废弃掉.
	 * 
	 * 若程序需要一个可复用的同步点，应该使用 CyclicBarrier (循环障碍) 来代替 CountDownLatch	
	 *  
	 *  */
	private final CountDownLatch latch = new CountDownLatch(1);
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @author qianye.zheng
	 */
	private void updateTotalSizeOfFileInDir(final File file)
	{
		Long fileSize = 0L;
		if (file.isFile())
		{
			fileSize = file.length();
		} else
		{
			final File[] children = file.listFiles();
			if (null != children)
			{
				for (File child : children)
				{
					if (child.isFile())
					{
						fileSize += child.length();
					} else
					{
						// 需要访问的文件数 自增1
						pendingFileVisits.incrementAndGet();
						executorService.execute(new Runnable() {
							/**
							 * @description 
							 * @author qianye.zheng
							 */
							@Override
							public void run()
							{
								updateTotalSizeOfFileInDir(child);
							}
						});
					}
				}
			}
		}
		// 加到总数
		totalSize.addAndGet(fileSize);
		if (pendingFileVisits.decrementAndGet() <= 0)
		{
			/*
			 * 释放线程闩
			 * 主线程也从await()调用中唤醒并返回目录的大小
			 */
			latch.countDown();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public Long getTotalSizeOfFile(final File file)
	{
		executorService = Executors.newFixedThreadPool(100);
		pendingFileVisits.incrementAndGet();
		updateTotalSizeOfFileInDir(file);
		try
		{
			latch.await(100, TimeUnit.SECONDS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		} finally
		{
			executorService.shutdown();
		}
		
		return totalSize.get();
	}
}
