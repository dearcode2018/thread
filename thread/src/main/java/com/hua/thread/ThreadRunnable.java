/**
 * ThreadRunnable.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread;

import com.hua.bean.thread.ShareDataBean;

/**
 * ThreadRunnable
 * 描述: 实现java.lang.Runnable接口 的定义线程类
 * @author  qye.zheng
 */
public final class ThreadRunnable implements Runnable
{
	private ShareDataBean shareData;
	
	/**
	 
	 实现了Runnable接口，只是将业务封装在这里，
	 这个类的对象并不意义上的线程，而只是Thread类
	 可以通过它来构造线程对象，并且来执行这里的业务
	 方法.
	 
	 
	 */

	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		try
		{
			System.out.println("ThreadRunnable.run()");
			// 用此类构造一个子线程，子线程执行到此，就是当前线程
			Thread subThread = Thread.currentThread();
			
			shareData.updatePassword("123456");
			
			//System.out.println(subThread.getName() +", 休眠之前");
			
			// 让当前线程休眠
			//Thread.sleep(3 * 1000);
			
			//System.out.println(subThread.getName() +", 休眠之后");
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return the shareData
	 */
	public ShareDataBean getShareData()
	{
		return shareData;
	}

	/**
	 * @param shareData the shareData to set
	 */
	public void setShareData(ShareDataBean shareData)
	{
		this.shareData = shareData;
	}
}
