/**
 * AssistTask.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.join;

/**
 * AssistTask
 * 描述: 辅助任务
 * @author  qye.zheng
 */
public class AssistTask implements Runnable
{

	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		System.out.println("辅助任务开始");
		try
		{
			Thread.sleep(8 * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("辅助任务结束");
	}

}
