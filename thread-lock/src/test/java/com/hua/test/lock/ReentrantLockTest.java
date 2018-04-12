/**
 * 描述: 
 * ReentrantLockTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.lock;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.lock.ReentrantLockCase;
import com.hua.test.BaseTest;


/**
 * 描述: 可重入锁
 * 
 * @author qye.zheng
 * ReentrantLockTest
 */
public final class ReentrantLockTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReentrantLock() {
		try {
			ReentrantLockCase reentrantLockCase = new ReentrantLockCase();
			
			/*
			 * 写值任务
			 */
			Runnable writeValueTask = new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					reentrantLockCase.writeValue(25.2);
				}
			};
			
			/*
			 * 读值任务
			 */
			Runnable readValueTask = new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					System.out.println("value = " + reentrantLockCase.readValue());
				}
			};
			// 任务执行服务，构造可缓存的线程池
			ExecutorService executorSerivce = Executors.newCachedThreadPool();
			// 期待对象
			Future<?> future = null;
			/*
			 * 同时执行 写值任务 3次，由于writeValue方法使用了锁机制，所以，实际
			 * 上会顺序执行
			 */
			for (int i = 0; i < 3; i++)
			{
				future = executorSerivce.submit(writeValueTask);
			}
			
			// 等待最后一个 写值任务 执行完
			future.get();
			
			// 执行 读值任务
			future = executorSerivce.submit(readValueTask);
			
			// 等待 读值任务 执行完
			future.get();
			
			// 关闭执行服务
			executorSerivce.shutdownNow();
		} catch (Exception e) {
			log.error("testReentrantLock =====> ", e);
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
