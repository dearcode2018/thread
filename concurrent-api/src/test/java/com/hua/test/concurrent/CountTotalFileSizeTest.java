/**
 * 描述: 
 * CountTotalFileSizeTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.concurrent;

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

import java.io.File;
import java.util.concurrent.ForkJoinPool;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.task.TotalFileSizeConcurrent;
import com.hua.task.TotalFileSizeConcurrentWLatch;
import com.hua.task.TotalFileSizeConcurrentWQueue;
import com.hua.task.TotalFileSizeForkJoinPool;
import com.hua.task.TotalFileSizeSequential;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CountTotalFileSizeTest
 */
public final class CountTotalFileSizeTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTotalFileSizeConcurrentForkJoinPool() {
		try {
			ForkJoinPool forkJoinPool = new ForkJoinPool();
			File file = new File("C:/Program Files/Common Files");
			file = new File("C:/Program Files");
			file = new File("C:/Windows/System32");
			final long start = System.nanoTime();
			Long size = forkJoinPool.invoke(new TotalFileSizeForkJoinPool.FileSizeFinder(file));
			final long end = System.nanoTime();
			
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
			log.info("testTotalFileSizeConcurrentForkJoinPool =====> " + size);
			
		} catch (Exception e) {
			log.error("testTotalFileSizeConcurrentForkJoinPool =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTotalFileSizeConcurrentWQueue() {
		try {
			File file = new File("C:/Program Files/Common Files");
			file = new File("C:/Program Files");
			file = new File("C:/Windows/System32");
			TotalFileSizeConcurrentWQueue totalFileSizeConcurrentWQueue = new TotalFileSizeConcurrentWQueue();
			final long start = System.nanoTime();
			Long size = totalFileSizeConcurrentWQueue.getTotalSizeOfFile(file);
			final long end = System.nanoTime();
			
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
			log.info("testTotalFileSizeConcurrentWQueue =====> " + size);
			
		} catch (Exception e) {
			log.error("testTotalFileSizeConcurrentWQueue =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTotalFileSizeConcurrentWLatch() {
		try {
			File file = new File("C:/Program Files/Common Files");
			file = new File("C:/Program Files");
			file = new File("C:/Windows/System32");
			TotalFileSizeConcurrentWLatch totalFileSizeConcurrentWLatch = new TotalFileSizeConcurrentWLatch();
			final long start = System.nanoTime();
			Long size = totalFileSizeConcurrentWLatch.getTotalSizeOfFile(file);
			final long end = System.nanoTime();
			
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
			log.info("testTotalFileSizeConcurrentWLatch =====> " + size);
			
		} catch (Exception e) {
			log.error("testTotalFileSizeConcurrentWLatch =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTotalFileSizeConcurrent() {
		try {
			TotalFileSizeConcurrent totalFileSizeConcurrent = new TotalFileSizeConcurrent();
			File file = new File("C:/Program Files/Common Files");
			file = new File("C:/Program Files");
			file = new File("C:/Windows/System32");
			final long start = System.nanoTime();
			long size = totalFileSizeConcurrent.getTotalSizeOfFilesInDir(file);
			final long end = System.nanoTime();
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
			log.info("testTotalFileSizeSequential =====> " + size);
			
			
		} catch (Exception e) {
			log.error("testTotalFileSizeConcurrent =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTotalFileSizeSequential() {
		try {
			File file = new File("C:/Program Files/Common Files");
			file = new File("C:/Program Files");
			file = new File("C:/Windows/System32");
			//file = new File("C:");
			final long start = System.nanoTime();
			long size = TotalFileSizeSequential.getTotalSizeOfFilesInDir(file);
			final long end = System.nanoTime();
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
			log.info("testTotalFileSizeSequential =====> " + size);
			
		} catch (Exception e) {
			log.error("testTotalFileSizeSequential =====> ", e);
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
