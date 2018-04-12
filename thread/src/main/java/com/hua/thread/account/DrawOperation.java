/**
  * @filename DrawOperation.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.thread.account;

import com.hua.util.RandomUtil;

 /**
 * @type DrawOperation
 * @description 取钱操作
 * @author qye.zheng
 */
public final class DrawOperation implements Runnable {

	private Account account;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public DrawOperation(final Account account) {
		this.account = account;
	}
	
	/**
	 * @description 
	 * @author qye.zheng
	 */
	@Override
	public void run() {
		int i = 300;
		while (true)
		{
			Double ammount = RandomUtil.nextInt(100) + 0.0;
			account.draw(ammount);
			i--;
		}

	}

}
