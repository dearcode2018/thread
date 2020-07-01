/**
  * @filename NotDaemonMainMethod.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.thread.daemon;

import java.util.Date;

 /**
 * @type NotDaemonMainMethod
 * @description 
 * @author qianye.zheng
 */
public class NotDaemonMainMethod
{
	
	/**
	 * TODO 只能用main方法来演示这种场景，junit中是无法展示的.
	 */
	
	/**
	 * 
	 * @description 
	 * @param args
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static void main(String[] args) throws Exception 
	{
		/*
		 * 设置为守护线程需要在线程start之前进行设置，
		 * 因此main线程是无法设置为守护线程的.
		 * 
		 */
		//Thread.currentThread().setDaemon(true);
		
		/*
		 * 线程设置为守护线程之后，当其他种类线程(非守护线程)结束之后，
		 * 守护线程也会被kill掉，因为没有存在必要.
		 */
		
		// 守护线程示例
		//daemon();
		
		// 非守护线程示例
		notDaemon();
	}
	
	/**
	 * 
	 * @description 
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static void daemon() throws Exception
	{

		Runnable r = new Runnable() {
			@Override
			public void run() {
				String daemon = (Thread.currentThread().isDaemon() ? "daemon"
						: "not daemon");
				while (true) {
					System.out.println("I'm running at " + new Date()
							+ ", I am " + daemon);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("I was interrupt, I am " + daemon);
					}
				}
			}

		};
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.start();
		Thread.sleep(3000);
		System.out.println("main thread exits");
	}
	
	/**
	 * 
	 * @description 
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static void notDaemon() throws Exception
	{

		Runnable r = new Runnable() {
			@Override
			public void run() {
				String daemon = (Thread.currentThread().isDaemon() ? "daemon"
						: "not daemon");
				while (true) {
					System.out.println("I'm running at " + new Date()
							+ ", I am " + daemon);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("I was interrupt, I am " + daemon);
					}
				}
			}

		};
		Thread t = new Thread(r);
		t.setDaemon(false);
		t.start();
		Thread.sleep(1000);
		System.out.println("main thread exits");
	}
	
}
