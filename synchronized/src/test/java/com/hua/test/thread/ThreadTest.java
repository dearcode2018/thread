/**
 * 描述: 
 * ThreadTest.java
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

import com.hua.bean.thread.ShareDataBean;
import com.hua.test.BaseTest;
import com.hua.thread.ThreadRunnable;


/**
 * 描述: 线程 - 测试
 * 
 * @author qye.zheng
 * ThreadTest
 */
public final class ThreadTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testThread() {
		try {
			
			log.info("testThread =====> minPriority = " + Thread.MIN_PRIORITY);
			log.info("testThread =====> normalPriority = " + Thread.NORM_PRIORITY);
			log.info("testThread =====> maxPriority = " + Thread.MAX_PRIORITY);
			
		} catch (Exception e) {
			log.error("testThread =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 主线程
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMainThread() {
		try {
			// 当前线程对象就是主线程
			mainThread = Thread.currentThread();
			
			// main
			log.info("testMainThread =====> " + mainThread.getName());
			// 5
			log.info("testMainThread =====> " + mainThread.getPriority());
			
			/*
			 主线程，就是运行入口方法(类似main方法)的线程
			 一旦主线程停止或休眠，则其接下来的代码则不再执行，
			 停止一定的时间或等待各种指令.
			 */
			
			log.info("testMainThread =====> 休眠之前");
			
			// 让主线程休眠，休眠之后，代码运行到此将暂停
			Thread.sleep(3 * 1000);
			
			log.info("testMainThread =====> 休眠之后");
			// 
			log.info("testMainThread =====> " + mainThread.isAlive());
			
		} catch (Exception e) {
			log.error("testMainThread =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSleep() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSleep =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 线程对象打断
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInterrupt() {
		try {
			/**
			 线程对象执行打断方法
			 
			 */
			ShareDataBean shareData = new ShareDataBean();
			ThreadRunnable o1 = new ThreadRunnable();
			o1.setShareData(shareData);
			
			ThreadRunnable o2 = new ThreadRunnable();
			o2.setShareData(shareData);
			
			// 子线程，通过 Thread 来构造出一个子线程
			subThread = new Thread(o1, "subThread");
			Thread s2 = new Thread(o2, "subThread 2");
			
			// 启动子线程
			subThread.start();
			
			Thread.sleep(1 * 1000);
			
			s2.start();
			
			System.out.println("打断第一个子线程的休眠，让其开始继续干活");
			/*
			 打断第一个子线程的休眠，让其开始继续干活
			 */
			s2.interrupt();
			
			// 主线程休眠
			Thread.sleep(15 * 1000);
			
		} catch (Exception e) {
			log.error("testInterrupt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 子线程
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSubThread() {
		try {
			
			ThreadRunnable o1 = new ThreadRunnable();
			// 子线程，通过 Thread 来构造出一个子线程
			subThread = new Thread(o1, "subThread");
			
			log.info("testSubThread =====> " + subThread.getName());
			log.info("testSubThread =====> " + subThread.getPriority());
			
			// 设置为守护线程
			//subThread.setDaemon(true);
			// 启动子线程
			subThread.start();
			
			/**
			 若是主线程比子线程提前结束，则子线程的一些
		      功能可能会受到影响. 例如 主线程已经结束，则子
		      线程的输出无法再展现.
			 */
			Thread.sleep(4 * 1000);
			
			
		} catch (Exception e) {
			log.error("testSubThread =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 守护线程
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGuardThread() {
		try {
			
			
		} catch (Exception e) {
			log.error("testGuardThread =====> ", e);
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
