/**
 * ThreadUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

/**
 * ThreadUtil
 * 描述: 线程 - 工具类
 * @author  qye.zheng
 */
public final class ThreadUtil
{

	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private ThreadUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 让当前线程休眠指定的秒数
	 * @author  qye.zheng
	 * @param second 秒
	 */
	public static void currentThreadSleep(final int second)
	{
		try
		{
			Thread.sleep(second * 1000);
		} catch (InterruptedException e)
		{
			System.out.println(Thread.currentThread().getName() + ", 被打断!");
			e.printStackTrace();
		}
	}

}
