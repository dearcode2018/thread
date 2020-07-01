/**
 * 描述: 
 * AccountDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.thread.account;

import org.junit.jupiter.api.Test;

import com.hua.util.ThreadUtil;


/**
 * 描述:  - 驱动器
 * @author  qye.zheng
 * AccountDriver
 */
public class AccountDriver
{
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private AccountDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean simulate()
	{
		boolean flag = false;
		try
		{
			Account account = new Account();
			account.setAccountNo("2000545799465");
			account.setBalance(10.0);
			// 存钱操作
			Runnable depositOperation = new DepositOperation(account);
			// 取钱操作
			Runnable drawOperation = new DrawOperation(account);
			
			// 存钱线程
			Thread depositThread = new Thread(depositOperation, "存钱线程");
			// 取钱线程
			Thread drawThread = new Thread(drawOperation, "取钱线程");
			
			depositThread.start();
			
			drawThread.start();
			
			final int second = 5;
			ThreadUtil.currentThreadSleep(second);
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean simulateNew()
	{
		boolean flag = false;
		try
		{
			Account account = new Account();
			account.setAccountNo("2000545799465");
			account.setBalance(10.0);
			
			// 存钱线程
			Thread depositThread = new Thread(() -> account.deposit(1.0D), "存钱线程") ;
			// 取钱线程
			Thread drawThread = new Thread(() -> account.draw(2.0D), "取钱线程");
			
			depositThread.start();
			
			drawThread.start();
			
			final int second = 5;
			ThreadUtil.currentThreadSleep(second);
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	
}
