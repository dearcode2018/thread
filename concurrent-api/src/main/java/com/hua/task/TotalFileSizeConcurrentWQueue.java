/**
  * @filename TotalFileSizeConcurrentWQueue.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @type TotalFileSizeConcurrentWQueue
 * @description 统计文件大小-并发-队列(阻塞)
 * @author qianye.zheng
 */
public class TotalFileSizeConcurrentWQueue
{
		
	private ExecutorService executorService;
	
	/* 新增要处理的目录 */
	private final AtomicLong pendingFileVisits = new AtomicLong();
	
	/* 阻塞队列: 存放各个线程输出的文件大小结果 */
	private final BlockingQueue<Long> totalSize = new ArrayBlockingQueue<Long>(500);
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @author qianye.zheng
	 */
	private void startExploreDir(final File file)
	{
		pendingFileVisits.incrementAndGet();
		executorService.execute(new Runnable() {
			/**
			 * @description 
			 * @author qianye.zheng
			 */
			@Override
			public void run()
			{
				exploreDir(file);
			}
		});
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @author qianye.zheng
	 */
	private void exploreDir(final File file)
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
						startExploreDir(child);
					}
				}
			}
		}
		try
		{
			totalSize.put(fileSize);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		pendingFileVisits.decrementAndGet();
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
		startExploreDir(file);
		Long totalValue = 0L;
		while (pendingFileVisits.get() > 0L || totalSize.size() > 0)
		{
			try
			{
				totalValue += totalSize.poll(10, TimeUnit.SECONDS);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		return totalValue;
	}
}
