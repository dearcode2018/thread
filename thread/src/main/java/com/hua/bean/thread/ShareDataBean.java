/**
 * ShareDataBean.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.bean.thread;

import com.hua.bean.BaseBean;

/**
 * ShareDataBean
 * 描述: 
 * @author  qye.zheng
 */
public final class ShareDataBean extends BaseBean
{
	/**
	 	共享数据bean，定义类级/实例级的同步(sychronized)方法
	 */

	private static final long serialVersionUID = 1L;
	
	/* 登录-用户名 (唯一) */
	private String username;
	
	/* 昵称 (用于显示) */
	private String nickname;
	
	/* 登录-密码 */
	private String password;
	
	private Object lock;
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public ShareDataBean()
	{
	}
	
	/**
	 * 
	 * 描述: 类级同步方法 
	 * @author  qye.zheng
	 * @return
	 */
	public static synchronized String staticMethod()
	{
		String result = "hello";
		System.out.println("ShareDataBean.staticMethod()");
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 实例级同步方法
	 * @author  qye.zheng
	 * @param newPassword
	 * @return
	 */
	public synchronized boolean updatePassword(final String newPassword)
	{
		try
		{
			System.out.println(Thread.currentThread().getName() + " 开始休眠");
			System.out.println(Thread.currentThread().getName() + ": " + Thread.interrupted());
			
			Thread.sleep(8 * 1000);
			
			System.out.println(Thread.currentThread().getName() + " 停止休眠");
			
			System.out.println(Thread.currentThread().getName() + ": " + Thread.interrupted());
			
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		boolean flag = true;
		// 修改密码字段
		this.password = newPassword;
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 方法中含有同步块
	 * @author  qye.zheng
	 * @param newUsername
	 * @return
	 */
	public boolean updateUsername(final String newUsername)
	{
		// 同步块
		synchronized (lock)
		{
			this.username = newUsername;
		}
		
		return true;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
