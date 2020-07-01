/**
  * @filename YieldThead.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.thread._static.yield;

import com.hua.bean.Counter;

 /**
 * @type YieldThead
 * @description 
 * @author qianye.zheng
 */
public class YieldThead implements Runnable
{

	private Counter commontObject;
	
	/* 1-访问sync1(), 2-访问sync2(), 3-访问unsync() */
	private int status;

	/**
	 * 
	 * @description 构造方法
	 * @param commontObject
	 * @param stauts
	 * @author qianye.zheng
	 */
	public YieldThead(Counter commontObject, int status)
	{
		this.commontObject = commontObject;
		this.status = status;
	}
	
	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void run()
	{
		switch (status)
		{
		/* 访问同步方法 */
		case 1:
			// 访问同步方法1
			commontObject.sync1();
			break;
			
		case 2:
			// 访问同步方法2
			commontObject.sync2();
			break;
			
		case 3:
			// 访问同步方法
			commontObject.sync3();
			break;
			
		case 4:
			// 访问同步方法
			commontObject.sync4();
			break;
			
		case 5:
			// 访问同步方法
			commontObject.sync5();
			break;

		/* 访问非同步方法 */
		case 20:
			// 访问非同步方法
			commontObject.unsync6();
			break;			
		}
	}

}
