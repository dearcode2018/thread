/**
 * 描述: 
 * WaitNotifyTest.java
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.Counter;
import com.hua.test.BaseTest;
import com.hua.thread.wn.CounterThead;
import com.hua.util.ThreadUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * WaitNotifyTest
 */
public final class WaitNotifyTest extends BaseTest {

	private Counter commontObject = new Counter();
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWaitAndNotify() {
		// 线程1 wait，线程2执行完成后notify，线程1继续执行
		try {
			// 包含有 wait()，进入阻塞状态，
			CounterThead counterThead1 = new CounterThead(commontObject, 3);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问另外一个同步方法，执行完了，notify其他wait的线程
			CounterThead counterThead2 = new CounterThead(commontObject, 4);
			Thread t2 = new Thread(counterThead2);
			t2.setName("thread-2");
			
			t1.start();
			/*
			 * 这种启动方式，t1 / t2启动顺序不确定，
			 * 为了验证的准确性，可以在t1启动后1秒再启动t2
			 */
			ThreadUtil.currentThreadSleep(1);
			
			t2.start();
			
			final int second = 20;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testWaitAndNotify =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessSyncMethod() {
		// 线程1 wait，线程2执行完成后notify，线程1继续执行
		try {
			// 包含有 wait()，进入阻塞状态，
			CounterThead counterThead1 = new CounterThead(commontObject, 3);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问另外一个同步方法，执行完了，notify其他wait的线程
			CounterThead counterThead2 = new CounterThead(commontObject, 4);
			Thread t2 = new Thread(counterThead2);
			t2.setName("thread-2");
			
			t1.start();
			/*
			 * 这种启动方式，t1 / t2启动顺序不确定，
			 * 为了验证的准确性，可以在t1启动后1秒再启动t2
			 */
			ThreadUtil.currentThreadSleep(1);
			
			t2.start();
			
			final int second = 20;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testAccessSyncMethod20 =====> ", e);
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
	 * 描述: 普通测试方法
	 ,@Test注解 不带参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNormal() {
		System.out.println("testNormal()");
	}
	
	/**
	 * 
	 * 描述: 期望发生异常-测试方法
	 ,@Test注解 异常
	 * @author qye.zheng
	 * 
	 */
	@Test(expected=NullPointerException.class)
	@SuppressWarnings("all")
	public void testException() {
		String str = null;
		System.out.println(str.length());
	}
	
	/**
	 * 
	 * 描述: 超时测试方法
	 ,@Test注解 指定运行时间 (单位 : 毫秒)
	 * @author qye.zheng
	 * 
	 */
	@Test(timeout=3000)
	public void testTimeOut() {
		System.out.println("testTimeOut()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Ignore("暂时忽略的方法")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@Before
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@After
	public void afterMethod() {
		System.out.println("afterMethod()");
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
