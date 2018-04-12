/**
 * 描述: 
 * CalculationConcurrentTest.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.task.PrimeFinder;
import com.hua.task.PrimeFinderTask;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CalculationConcurrentTest
 */
public final class CalculationConcurrentTest extends BaseTest {

	private int number = 10000000;
	
	/**
	 * 
	 * 描述: 统计素数的数量，没有多线程并发处理
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCountPrimeWithoutConcurrency() {
		try {
			
			/*
			 * private int number = 10000000;
			 * Number of primes under 10000000 is 664579
			 * time seconds token is 5.878762004
			 */
			new PrimeFinder().timeAndCompute(number);
			
		} catch (Exception e) {
			log.error("testFindPrimeWithoutConcurrency =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 统计素数的数量，采用多线程并发处理
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCountPrimeConcurrency() {
		try {
			/**
			 * 计算密集型 应用 线程数不宜太多，等于处理器数量即可
			 */
			//ExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
			// 当前处理器核心数是 8
			ExecutorService executorService = Executors.newScheduledThreadPool(8);
			List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
			
			/*
			 * private int number = 10000000;
			 * Number of primes under 10000000 is 664579
			 * time seconds token is 5.878762004
			 */
			// 当前处理器核心数是 8，因此将任务拆分成8个子任务
			Integer[][] groups = {{1, 1250000}, {1250001, 2500000}, {2500001, 3750000}, {3750001, 5000000}, 
					{5000001, 6250000}, {6250001, 7500000}, {7500001, 8750000}, {8750001, 10000000}};
			
			for (Integer[] e : groups)
			{
				callables.add(new PrimeFinderTask(e[0], e[1]));
			}
			final long start = System.nanoTime();
			List<Future<Integer>> futures = executorService.invokeAll(callables);
			final long end = System.nanoTime();
			Long total = 0L;
			for (Future<Integer> f : futures)
			{
				if (f.isDone())
				{
					total += f.get();
				}
			}
			System.out.printf("Number of primes is %d\n", total);
			System.out.println("time seconds token is " + (end - start) / 1.0e9);
		} catch (Exception e) {
			log.error("testCountPrimeConcurrency =====> ", e);
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
