/**
  * @filename Counter.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

 /**
 * @type Counter
 * @description 计数器
 * @author qianye.zheng
 */
public class Counter
{
	/**
	 * volatile 修饰变量，线程在每次使用变量的时候，都会读取变量修改后的值.
	 */
	
	public static int countNoVolatile = 0;
	
	/*  */
	public static volatile int countWithVolatile = 0;
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public static void incCountNoVolatile()
	{
		// 延迟 1 毫秒，使得结果更加明显
		try
		{
			Thread.sleep(1);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		countNoVolatile++;
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public static void incCountWithVolatile()
	{
		// 延迟 1 毫秒，使得结果更加明显
		try
		{
			Thread.sleep(1);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		countWithVolatile++;
	}
}
