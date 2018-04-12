/**
 * 描述: 
 * EggStarter.java
 * @author	qye.zheng
 * @date	2014-7-1
 *  version 1.0
 */
package com.hua.thread.eg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hua.util.ThreadUtil;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * @date  2014-7-1
 * EggStarter
 */
public final class EggStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * @date	2014-7-1
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		//EggDriver.simulate();
		EggDriver.simulate();
		//List<Object> eggs = new ArrayList<Object>(5);
		//System.out.println(eggs.size());
		//final int second = 5;
		//ThreadUtil.currentThreadSleep(second);
	}

}
