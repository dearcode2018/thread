/**
 * 描述: 
 * InterruptStarter.java
 * @author	qye.zheng
 * @date	2014-7-1
 *  version 1.0
 */
package com.hua.thread.interrupt;

import org.junit.Test;

import com.hua.thread.join.MainTask;
import com.hua.util.ThreadUtil;

/**
 * 描述: 线程Join - 启动器
 * @author  qye.zheng
 * @date  2014-7-1
 * InterruptStarter
 */
public final class InterruptStarter
{
	
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
		
		try {
			mainTaskThread.join(); // 主线程等待它执行完成
		} catch (InterruptedException e) { // 主线程被打断，捕获后将目标线程也打断
			mainTaskThread.interrupt();
		}
	}

}
