/**
 * ThreadExtends.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread;

import com.hua.util.ThreadStudyUtil;

/**
 * ThreadExtends
 * 描述: 继承java.lang.Thread 的定义线程类
 * @author  qye.zheng
 */
public final class ThreadExtends extends Thread
{
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public ThreadExtends()
	{
		super();
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public ThreadExtends(final String name)
	{
		super(name);
	}
	
	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		System.out.println("ThreadExtends.run() 1");
		
		ThreadStudyUtil.timeConsume(2);
		
		System.out.println("ThreadExtends.run() 2");
	}
}
