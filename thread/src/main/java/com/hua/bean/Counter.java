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
	 * 1）一个线程在访问一个对象的同步方法时，其他线程可以同时访问这个对象的非同步方法；
	 * 2）一个线程在访问一个对象的同步方法时，其他线程不能同时访问此同步方法
	 * 3）一个线程在访问一个对象的同步方法时，其他线程不能同时访问这个对象的任一个同步方法
	 * 同步方法A中使用了该对象的wait方法，放弃对象锁，其他线程可以访问该对象的其他同步方法，
	 * 
	 * synchronized 就是一把对象锁，只能被一个线程持有，方法或方法块执行完之后，自动释放锁.
	 * 
	 */
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
	
	/**
	 * 
	 * @description 同步方法1
	 * @author qianye.zheng
	 */
	public synchronized void sync1()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问sync1()方法");
		/**
		 * 调用sleep方法不会释放锁
		 * sleep方法本来就是和锁没有关系的，因为是一个线程用于管理自己的方法，不涉及线程通信
		 */
		// 当前子线程休眠
		try
		{
			Thread.sleep(7 * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("休眠正常结束");
		
		System.out.println("sync1()方法end");
	}
	
	/**
	 * 
	 * @description 同步方法2
	 * @author qianye.zheng
	 */
	public synchronized void sync2()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问sync2()方法");
		
		System.out.println("sync2()方法end");
	}
	
	/**
	 * 
	 * @description 同步方法3
	 * @author qianye.zheng
	 */
	public synchronized void sync3()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问sync3()方法");
		/**
		 * 调用sleep方法不会释放锁
		 * sleep方法本来就是和锁没有关系的，因为是一个线程用于管理自己的方法，不涉及线程通信
		 */

		/**
		 * wait()，调用
		 */
		try
		{
			System.out.println("线程: " + Thread.currentThread().getName() + ", wait()");
			this.wait();
			System.out.println("线程: " + Thread.currentThread().getName() + " 现场恢复");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("sync3()方法end");
	}
	
	/**
	 * 
	 * @description 同步方法4
	 * @author qianye.zheng
	 */
	public synchronized void sync4()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问sync4()方法");
		/**
		 * 调用sleep方法不会释放锁
		 * sleep方法本来就是和锁没有关系的，因为是一个线程用于管理自己的方法，不涉及线程通信
		 */
		// 当前子线程休眠
		try
		{
			Thread.sleep(7 * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		//
		this.notify();
		System.out.println("休眠正常结束");
		
		System.out.println("sync4()方法end");
	}
	
	/**
	 * 
	 * @description 同步方法4
	 * @author qianye.zheng
	 */
	public synchronized void sync5()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问sync5()方法");
		/**
		 * 调用sleep方法不会释放锁
		 * sleep方法本来就是和锁没有关系的，因为是一个线程用于管理自己的方法，不涉及线程通信
		 */
		System.out.println("线程: " + Thread.currentThread().getName() + ", yield之前");
		/**
		 * 表示当前线程乐意让出/屈服对当前CPU资源的使用，以便于其他线程可以争用CPU
		 */
		Thread.yield();
		System.out.println("线程: " + Thread.currentThread().getName() + ", yield过后，继续执行");
		System.out.println("sync5()方法end");
	}
	
	/**
	 * 
	 * @description 非同步方法
	 * @author qianye.zheng
	 */
	public void unsync()
	{
		System.out.println("线程: " + Thread.currentThread().getName() + ", 正在访问unsync()方法");
		
		System.out.println("unsync()方法end");
	}
}
