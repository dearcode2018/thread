/**
 * 描述: 
 * LockTest.java
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

import java.util.concurrent.locks.Lock;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * LockTest
 */
public final class LockTest extends BaseTest {

	/**
	 * Locker
	 * 在(单机)多线程环境中一个重要的概念是锁，如果一个资源是多个线程共享的公共资源，为了保证
	 * 数据(资源)的完整性，在进行事务操作时需要将共享资源锁定，这样可以确保在做事务性操作时只有
	 * 一个线程能对资源进行操作，从而确保数据的完整性，在jdk 5.0之前，锁的功能是由synchroized关键字
	 * 来实现的.
	 * 
	 * 可重入锁
	 * ReentrantLock
	 * 
	 * ReentrantReadWriteLock
	 * 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLock() {
		try {
			
			Lock lock = null;
			
			
		} catch (Exception e) {
			log.error("testLock =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 可重入锁
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReentrantLock() {
		try {
			
			Lock lock = null;
			
			
		} catch (Exception e) {
			log.error("testReentrantLock =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 可重入读写锁
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReentrantReadWriteLock() {
		try {
			
			Lock lock = null;
			
			
		} catch (Exception e) {
			log.error("testReentrantReadWriteLock =====> ", e);
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
