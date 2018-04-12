/**
 * 描述: 
 * ThreadStudyUtil.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.util;

/**
 * 描述: 
 * @author  qye.zheng
 * ThreadStudyUtil
 */
public final class ThreadStudyUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private ThreadStudyUtil()
	{
	}
	
	/**
	 * 
	 * 描述:  耗时动作
	 * @author qye.zheng
	 */
	public static void timeConsume(final int size)
	{
		long total = size * 100000000;
		for(long k= 0; k < total; k++);
	}

}
