/**
  * @filename MyTask.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import com.hua.util.ThreadUtil;

 /**
 * @type MyTask
 * @description 
 * @author qianye.zheng
 */
public class MyTask implements Runnable
{

	private String taskName;
	
	
	/**
	 * @description 构造方法
	 * @param taskName
	 * @author qianye.zheng
	 */
	public MyTask(String taskName)
	{
		super();
		this.taskName = taskName;
	}



	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void run()
	{
		System.out.println("正在执行task: " + taskName);
		ThreadUtil.currentThreadSleep(4);
		System.out.println("task: " + taskName + " 执行完毕");
	}

}
