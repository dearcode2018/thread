/**
  * @filename Account.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.thread.account;

 /**
 * @type Account
 * @description 
 * @author qye.zheng
 */
public final class Account {
	
	/* 帐号 */
	private String accountNo;
	
	/* 余额 */
	private Double balance = 0.0;
	
	/* 帐户是否为空，初始为false表示存款为0 */
	private boolean empty = true;

	/**
	 * 
	 * @description 存钱 操作
	 * @param depositAmount 存款值
	 * @author qye.zheng
	 */
	public final synchronized void deposit(final Double depositAmount)
	{
		if (balance <= 0)
		{
			empty = true;
		}
		if (empty)
		{ // 帐户没钱
			System.out.println("存钱，" + Thread.currentThread().getName() + " 存款值: " + depositAmount.doubleValue());
			balance += depositAmount;
			// 帐户不为空
			empty = false;
			this.notify();
		} else
		{ // 帐户有钱
			try {
				System.out.println("存钱 ，" + Thread.currentThread().getName() + " 帐户有钱，进入等待状态");
				//
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @description 取钱 操作
	 * @param drawAmount 取款值
	 * @author qye.zheng
	 */
	public final synchronized void draw(final Double drawAmount)
	{
		if (balance <= 0)
		{
			empty = true;
		} else
		{
			empty = false;
		}
		if (empty)
		{ // 帐户没钱
			System.out.println("取钱，" + Thread.currentThread().getName() + " 帐户没钱，进入等待状态");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else
		{ // 帐户有钱
			System.out.println("取钱，" + Thread.currentThread().getName() + " 取款值: " + ((balance - drawAmount) > 0 ? drawAmount : balance));
			balance -= drawAmount;
			if (balance <= 0)
			{
				balance = 0.0;
				empty = true;
			}
			// 唤醒存钱线程
			this.notify();
		}
	}	
	
	/**
	 * @return the accountNo
	 */
	public final String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public final void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the balance
	 */
	public final Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public final void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
