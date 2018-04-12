/**
 * 描述: 
 * ThreadPoolTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.pool;

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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.task.MyTask;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ThreadPoolTest
 */
public final class ThreadPoolTest extends BaseTest {

	public static void main(String[] args)
	{
		threadPool();
		
		//threadPoolOfExecutors();
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	private static void threadPool() {
		try {
			/**
			 * 1) 当线程池中的任务数大于corePoolSize时，新增的任务会放入任务缓存队列中
			 * 
			 * 2) 当任务缓存队列满了之后，便创建新的线程
			 * 
			 * 3) 当创建的最大线程数超过maxPoolSize时，就会抛出任务拒绝异常
			 * 
			 */
			int corePoolSize = 5;
			int maxPoolSize = 12;
			int task = 12;
			task = 17;
			BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(6);
			ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 2, TimeUnit.SECONDS, blockQueue);
			MyTask myTask = null;
			for (int i = 1; i <= task; i++)
			{
				myTask = new MyTask("name" + i);
				executor.execute(myTask);
				//executor.submit(myTask);
				System.out.println("线程池中线程数目: " + executor.getPoolSize());
				System.out.println("等待队列中任务数目: " + executor.getQueue().size());
				System.out.println("已经执行完毕任务数目: " + executor.getCompletedTaskCount());
				System.out.println("==============");
			}
			
			// 关闭线程池
			executor.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public static void threadPoolOfExecutors() {
		try {
			/**
			 * 通过 Executors 来创建 ExecutorService 来执行 任务
			 */
			int corePoolSize = 5;
			int maxPoolSize = 12;
			BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(6);
			ExecutorService executorService = Executors.newCachedThreadPool();
			MyTask myTask = null;
			for (int i = 0; i < 15; i++)
			{
				myTask = new MyTask("name" + i);
				executorService.execute(myTask);
/*				//executor.submit(myTask);
				System.out.println("==============");
				System.out.println("线程池中线程数目: " + executorService.getPoolSize());
				System.out.println("等待队列中任务数目: " + executorService.getQueue().size());
				System.out.println("已经执行完毕任务数目: " + executorService.getCompletedTaskCount());
				System.out.println("==============");*/
			}
			
			// 关闭线程池
			executorService.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testThreadPool() {
		try {
			/**
			 * 1) 当线程池中的任务数大于corePoolSize时，新增的任务会放入任务缓存队列中
			 * 
			 * 2) 当任务缓存队列满了之后，便创建新的线程
			 * 
			 * 3) 当创建的最大线程数超过maxPoolSize时，就会抛出任务拒绝异常
			 * 
			 */
			int corePoolSize = 5;
			int maxPoolSize = 12;
			BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(6);
			ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 2, TimeUnit.SECONDS, blockQueue);
			MyTask myTask = null;
			for (int i = 0; i < 12; i++)
			{
				myTask = new MyTask("name" + i);
				executor.execute(myTask);
				//executor.submit(myTask);
				System.out.println("线程池中线程数目: " + executor.getPoolSize());
				System.out.println("等待队列中任务数目: " + executor.getQueue().size());
				System.out.println("已经执行完毕任务数目: " + executor.getCompletedTaskCount());
				System.out.println("==============");
			}
			
			// 关闭线程池
			executor.shutdown();
			
		} catch (Exception e) {
			log.error("testThreadPool =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testThreadPoolOfExecutors() {
		try {
			/**
			 * 通过 Executors 来创建 ExecutorService 来执行 任务
			 */
			int corePoolSize = 5;
			int maxPoolSize = 12;
			BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(6);
			ExecutorService executorService = Executors.newCachedThreadPool();
			MyTask myTask = null;
			for (int i = 0; i < 15; i++)
			{
				myTask = new MyTask("name" + i);
				executorService.execute(myTask);
/*				//executor.submit(myTask);
				System.out.println("==============");
				System.out.println("线程池中线程数目: " + executorService.getPoolSize());
				System.out.println("等待队列中任务数目: " + executorService.getQueue().size());
				System.out.println("已经执行完毕任务数目: " + executorService.getCompletedTaskCount());
				System.out.println("==============");*/
			}
			
			// 关闭线程池
			executorService.shutdown();
			
		} catch (Exception e) {
			log.error("testThreadPoolOfExecutors =====> ", e);
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
