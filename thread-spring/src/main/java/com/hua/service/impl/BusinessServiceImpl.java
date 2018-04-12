/**
  * @filename BusinessServiceImpl.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hua.service.BusinessService;

 /**
 * @type BusinessServiceImpl
 * @description 
 * @author qianye.zheng
 */
public class BusinessServiceImpl extends CoreServiceImpl implements
		BusinessService
{
	@Resource
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	private long startTs;
	
	private long endTs;
	
	private boolean finished = false;
	
	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void doSomething(int times)
	{
		threadPoolTaskExecutor.execute(new Runnable() {
			/**
			 * @description 
			 * @author qianye.zheng
			 */
			@Override
			public void run()
			{
				// 具体要做的事情
				printMsg("hello world", times);
			}
		});
	}
	
	/**
	 * 
	 * @description 
	 * @param msg
	 * @param times
	 * @author qianye.zheng
	 */
	private void printMsg(final String msg, final int times)
	{
		if (0 == times)
		{
			System.out.println("startTs = " + System.currentTimeMillis());
			startTs = System.currentTimeMillis();
		}
		
		if (90000 == times)
		{
			System.out.println("endTs = " + System.currentTimeMillis());
			endTs = System.currentTimeMillis();
			finished = true;
		}

		for (long i = 0; i < 9000; i++)
		{
			for (long j = 0; j < 90; j++)
			{
				
			}
		}
		//System.out.println("msg: " + (msg + times));
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public long getConsumeTs()
	{
		return endTs - startTs;
	}

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean finished()
	{
		return finished;
	}
	
	
}
