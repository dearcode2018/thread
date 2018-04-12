/**
 * 描述: 
 * AccountTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.thread;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.thread.account.Account;
import com.hua.thread.account.DepositOperation;
import com.hua.thread.account.DrawOperation;
import com.hua.util.ThreadUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * AccountTest
 */
public final class AccountTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccount() {
		try {
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
			
		} catch (Exception e) {
			log.error("testAccount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 多个存 多个取
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccountMultiple() {
		try {
			Account account = new Account();
			account.setAccountNo("2000545799465");
			account.setBalance(10.0);
			// 存钱操作
			Runnable depositOperation = new DepositOperation(account);
			// 取钱操作
			Runnable drawOperation = new DrawOperation(account);
			
			
			
			// 存钱线程
			Thread depositThread1 = new Thread(depositOperation, "存钱线程1");
			// 存钱线程
			Thread depositThread2 = new Thread(depositOperation, "存钱线程2");
			// 存钱线程
			Thread depositThread3 = new Thread(depositOperation, "存钱线程3");
			
			// 取钱线程
			Thread drawThread1 = new Thread(drawOperation, "取钱线程1");
			// 取钱线程
			Thread drawThread2 = new Thread(drawOperation, "取钱线程2");
			// 取钱线程
			Thread drawThread3 = new Thread(drawOperation, "取钱线程3");
			
			depositThread1.start();
			depositThread2.start();
			depositThread3.start();
			
			drawThread1.start();
			drawThread2.start();
			drawThread3.start();
			
			final int second = 10;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testAccountMultiple =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 多个存钱线程
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDespositOnly() {
		try {
			Account account = new Account();
			account.setAccountNo("2000545799465");
			account.setBalance(10.0);
			// 存钱操作
			Runnable depositOperation = new DepositOperation(account);
			
			// 存钱线程1
			Thread depositThread1 = new Thread(depositOperation, "存钱线程1");
			
			// 存钱线程2
			Thread depositThread2 = new Thread(depositOperation, "存钱线程2");
			
			depositThread1.start();
			depositThread2.start();
			
			
			final int second = 5;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testDespositOnly =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 多个取钱线程
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDrawOnly() {
		try {
			Account account = new Account();
			account.setAccountNo("2000545799465");
			account.setBalance(100.0);
			// 取钱操作
			Runnable drawOperation = new DrawOperation(account);
			
			// 取钱线程1
			Thread drawThread1 = new Thread(drawOperation, "取钱线程1");
			
			// 取钱线程2
			Thread drawThread2 = new Thread(drawOperation, "取钱线程2");
			
			drawThread1.start();
			drawThread2.start();
			
			final int second = 5;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testDrawOnly =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
