/**
 * 描述: 
 * SleepStarter.java
 * @author	qye.zheng
 * @date	2014-7-1
 *  version 1.0
 */
package com.hua.thread._static.sleep;

import org.junit.Test;

import com.hua.util.ThreadUtil;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * @date  2014-7-1
 * SleepStarter
 */
public final class SleepStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @date	2014-7-1
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		final Runnable sleep = new SleepRunnable();
		final Thread sleepThread = new Thread(sleep, "sleep 子线程");
		
		sleepThread.start();
		
		sleepThread.interrupt();
		
		// main线程打断自己
		//Thread.currentThread().interrupt();
		
		System.out.println("main ...");
		
		final int second = 8;
		ThreadUtil.currentThreadSleep(second);
	}

}
