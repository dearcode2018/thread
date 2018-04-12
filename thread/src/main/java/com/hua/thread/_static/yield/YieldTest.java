/**
 * 描述: 
 * YieldTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.thread._static.yield;

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
import com.hua.util.ThreadUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * YieldTest
 */
public final class YieldTest extends BaseTest {

	private Counter commontObject = new Counter();
	
	/**
	 * 
	 * 描述: 让当前线程暂停,转入就绪状态,让系统的线程调度器重新调度一次
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testYield() {
		// yield() 让当前线程暂停,转入就绪状态,让系统的线程调度器重新调度一次
		try {
			// 包含有 wait()，进入阻塞状态，
			YieldThead counterThead1 = new YieldThead(commontObject, 5);
			Thread t1 = new Thread(counterThead1);
			t1.setName("thread-1");
			// 访问另外一个同步方法，执行完了，notify其他wait的线程
			YieldThead counterThead2 = new YieldThead(commontObject, 20);
			Thread t2 = new Thread(counterThead2);
			t2.setName("thread-2");
			
			t1.start();
			
			t2.start();
			
			final int second = 10;
			ThreadUtil.currentThreadSleep(second);
			
		} catch (Exception e) {
			log.error("testYield =====> ", e);
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
