/**
 * 描述: 
 * SynchronizedTest.java
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

import com.hua.bean.Counter;
import com.hua.test.BaseTest;
import com.hua.thread.wn.CounterThead;
import com.hua.util.ThreadUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SynchronizedTest
 */
public final class SynchronizedTest extends BaseTest {

	/* 同步对象 */
	private Counter commontObject = new Counter();
	
	/**
	 * 1）一个线程在访问一个对象的同步方法时，其他线程可以同时访问这个对象的非同步方法；
	 * 2）一个线程在访问一个对象的同步方法时，其他线程不能同时访问此同步方法
	 * 3）一个线程在访问一个对象的同步方法时，其他线程不能同时访问这个对象的任一个同步方法
	 * 
	 * 同步方法A中使用了该对象的wait方法，放弃对象锁，其他线程可以访问该对象的其他同步方法，
	 */
	
	/**
	 * 
	 * 描述: 一个线程在访问一个对象的同步方法时，其他线程可以同时访问这个对象的非同步方法；
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessSyncMethod1() {
		//  1）一个线程在访问一个对象的同步方法时，其他线程可以同时访问这个对象的非同步方法；
		try {
			CounterThead counterThead1 = new CounterThead(commontObject, 1);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问非同步方法
			CounterThead counterThead2 = new CounterThead(commontObject, 20);
			Thread t2 = new Thread(counterThead2);
			t2.setName("thread-2");
			
			t1.setDaemon(true);
			t1.start();
			/*
			 * 这种启动方式，t1 / t2启动顺序不确定，
			 * 为了验证的准确性，可以在t1启动后1秒再启动t2
			 */
			ThreadUtil.currentThreadSleep(1);
			
			
			t2.setDaemon(true);
			t2.start();

			
			//final int second = 20;
			//ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testAccessSyncMethod1 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 一个线程在访问一个对象的同步方法时，其他线程不能同时访问此同步方法
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessSyncMethod2() {
		// 2）一个线程在访问一个对象的同步方法时，其他线程不能同时访问此同步方法
		try {
			CounterThead counterThead1 = new CounterThead(commontObject, 1);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问同一个同步方法
			CounterThead counterThead2 = new CounterThead(commontObject, 1);
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
			log.error("testAccessSyncMethod2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 一个线程在访问一个对象的同步方法时，其他线程不能同时访问这个对象的任一个同步方法
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessSyncMethod3() {
		// 3）一个线程在访问一个对象的同步方法时，其他线程不能同时访问这个对象的任一个同步方法
		try {
			CounterThead counterThead1 = new CounterThead(commontObject, 1);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问另外一个同步方法
			CounterThead counterThead2 = new CounterThead(commontObject, 2);
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
			log.error("testAccessSyncMethod3 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccessSyncMethod20() {
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
