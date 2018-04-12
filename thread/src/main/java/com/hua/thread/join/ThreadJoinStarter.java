/**
 * 描述: 
 * ThreadJoinStarter.java
 * @author	qye.zheng
 * @date	2014-7-1
 *  version 1.0
 */
package com.hua.thread.join;

import org.junit.Test;

import com.hua.util.ThreadUtil;

/**
 * 描述: 线程Join - 启动器
 * @author  qye.zheng
 * @date  2014-7-1
 * ThreadJoinStarter
 */
public final class ThreadJoinStarter
{

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @date	2014-7-1
	 */
	@Test
	public void testMainTaskWaitAssistTask()
	{
		// 主任务
		final Runnable mainTask = new MainTask();
		final Thread mainTaskThread = new Thread(mainTask, "主任务");
		mainTaskThread.start();
		
		// 等待时间，不是马上执行 interrupt操作
		ThreadUtil.currentThreadSleep(3);
		
		/*
		 打断当前的等待，继续往下执行
		 此时主线程没有在休眠.
		 */
		//mainTaskThread.interrupt();
		
		final int second = 20;
		ThreadUtil.currentThreadSleep(second);
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @date	2014-7-1
	 */
	@Test
	public void testMainTaskInterrupt()
	{
		// 主任务
		final Runnable mainTask = new MainTask();
		final Thread mainTaskThread = new Thread(mainTask, "主任务");
		mainTaskThread.start();
		
		// 设置等待时间，不是马上执行 interrupt操作
		ThreadUtil.currentThreadSleep(3);
		
		/*
		 打断当前的等待，继续往下执行
		 此时主线程没有在休眠.
		 */
		mainTaskThread.interrupt();
		
		final int second = 20;
		ThreadUtil.currentThreadSleep(second);
	}

}
