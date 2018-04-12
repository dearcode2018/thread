/**
 * SleepRunnable.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread._static.sleep;

/**
 * SleepRunnable
 * 描述: 
 * @author  qye.zheng
 */
public final class SleepRunnable implements Runnable
{

	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		try
		{
			System.out.println("休眠开始");
			/**
			 * 调用sleep方法不会释放锁
			 * sleep方法本来就是和锁没有关系的，因为是一个线程用于管理自己的方法，不涉及线程通信
			 * 
			 * sleep是一个静态方法，和线程对象无关，因此和对象锁就没有关系.
			 * 
			 */
			// 当前子线程休眠
			Thread.sleep(5 * 1000);
			System.out.println("休眠正常结束");
		} catch (InterruptedException e)
		{
			System.out.println(Thread.currentThread().getName() + ", 被打断!");
			System.out.println("休眠异常停止");
			e.printStackTrace();
		}
	}

}
