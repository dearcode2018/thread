/**
 * MainTask.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.join;

/**
 * MainTask
 * 描述: 主任务
 * @author  qye.zheng
 */
public final class MainTask implements Runnable
{

	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		System.out.println("主任务开始");
		
		// 辅助任务线程
		final Runnable assistTask = new AssistTask();
		// 加入其他辅助线程，并且等待辅助线程执行完毕
		final Thread assistTaskThread = new Thread(assistTask);
		assistTaskThread.setName("辅助任务");
		try
		{
			// 先启动，然后再join
			assistTaskThread.start();
			/*
			 * 主任务可以等待辅助任务的(超时)时间，超过指定时间之后，
			 * 主任务继续执行，不再等待辅助任务
			 * 或者 主任务线程可以调用interrupt()方法
			 */
			long timeout = 10 * 1000;
			assistTaskThread.join();
			//assistTaskThread.join(timeout);
		} catch (InterruptedException e)
		{
			System.out.println("主任务中断该等待，不再等待辅助线程)");
			// 被打断的线程会继续执行，只是主业务线程不再等待辅助业务线程了，而是打断后继续执行
			assistTaskThread.interrupt();
		}
		/*
		 有可能超时，不再等待，这样如果共享的数据，可以知道
		 究竟辅助线程的执行结果，就可以对接下来的执行流程做一个资源判断
		 */
		System.out.println(Thread.currentThread().getName() + ", 继续执行");
		System.out.println(Thread.currentThread().getName() + ", 结束");
	}

}
