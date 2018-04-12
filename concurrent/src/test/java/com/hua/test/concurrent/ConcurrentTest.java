/**
 * 描述: 
 * ConcurrentTest.java
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
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.spi.TransactionalWriter;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.hua.task.SearchUserTaskCallable;
import com.hua.task.SearchUserTaskCallable2;
import com.hua.test.BaseTest;


/**
 * 描述: 并发技术 - 测试
 * 
 * @author qye.zheng
 * ConcurrentTest
 */
public final class ConcurrentTest extends BaseTest {
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetAvailableProcessor() {
		try {
			/**
			 *  获取可用的处理器个数(即核心数量，多少核)
			 *  windows 操作系统下可以在设备管理器-处理器中看到处理个数
			 *  或在任务管理器中 性能 --> CPU使用记录中看有多少个记录窗口，即为多少核的处理器.
			 *  
			 *  
			 *  
			 */
			log.info("testGetAvailableProcessor =====> " + Runtime.getRuntime().availableProcessors());
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 在主线程中执行多个任务，每个任务采用多线程方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExecuteMultipleTask() {
		List<Future<String>> futures = null;
		List<Future<String>> futures2 = null;
		List<Callable<String>> callables = new ArrayList<Callable<String>>();
		List<Callable<String>> callables2 = new ArrayList<Callable<String>>();
		try {
			/*
			 * 创建一个持有20个线程的线程池
			 */
			ExecutorService executorService = Executors.newScheduledThreadPool(20);
			Integer pageSize = 50;
			Integer currentPage = 1;
			SearchUserTaskCallable callable = null;
			SearchUserTaskCallable2 callable2 = null;
			/**
			 * 取20页的数据，每个线程去取一页的数据
			 */
			for (int i = 0; i < 20; i++)
			{
				currentPage = i + 1;
				callable = new SearchUserTaskCallable(pageSize);
				callable.setCurrentPage(currentPage);
				/*
				 * 执行一个可运行的任务，没有返回值
				 * 即在主线程中无法获得任务的返回值
				 */
				callables.add(callable);
			}
			
			/**
			 * 取20页的数据，每个线程去取一页的数据
			 */
			for (int i = 0; i < 20; i++)
			{
				currentPage = i + 1;
				callable2 = new SearchUserTaskCallable2(pageSize);
				callable2.setCurrentPage(currentPage);
				/*
				 * 执行一个可运行的任务，没有返回值
				 * 即在主线程中无法获得任务的返回值
				 */
				callables2.add(callable2);
			}
			
			
			/**
			 * 在主线程中 使用 线程池启动任务，用多行代码启动多个任务队列的时候，
			 * 是按照顺序来执行的，要等上一个任务队列处理完毕再执行下一个
			 */
			
			/*
			 * 批量调用所有的，定义超时时间，超时时间是所有任务的总超时时间，不是单个
			 */
			futures = executorService.invokeAll(callables, 45, TimeUnit.SECONDS);
			
			/*
			 * 批量调用所有的，定义超时时间，超时时间是所有任务的总超时时间，不是单个
			 */
			futures2 = executorService.invokeAll(callables2, 45, TimeUnit.SECONDS);
			
			Thread.sleep(70 * 1000);
			// 关闭线程服务
			executorService.shutdown();
			int count = 0;
			// 批量输出结果
			for (final Future<String> f : futures)
			{
				if (!f.isCancelled() && f.isDone())
				{
					count++;
					System.out.println(f.get());
					
				}
			}
			log.info("testExecuteMultipleTask =====> count = " + count);
			
			// 批量输出结果
			for (final Future<String> f : futures2)
			{
				if (!f.isCancelled() && f.isDone())
				{
					count++;
					System.out.println(f.get());
					
				}
			}
			log.info("testExecuteMultipleTask =====> count = " + count);
		} catch (Exception e) {
			log.error("testCallWebserviceConcurrent003 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 在主线程中执行多个任务，每个任务采用多线程方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExecuteTaskCompletion() {
		List<Future<String>> futures = new ArrayList<Future<String>>();
		Future<String> future = null;
		try {
			/*
			 * 创建一个持有20个线程的线程池
			 */
			ExecutorService executorService = Executors.newScheduledThreadPool(20);
			
			CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
			
			Integer pageSize = 50;
			Integer currentPage = 1;
			SearchUserTaskCallable callable = null;

			
			
			/**
			 * 取20页的数据，每个线程去取一页的数据
			 */
			for (int i = 0; i < 20; i++)
			{
				currentPage = i + 1;
				callable = new SearchUserTaskCallable(pageSize);
				callable.setCurrentPage(currentPage);
				/*
				 * 执行一个可运行的任务，没有返回值
				 * 即在主线程中无法获得任务的返回值
				 */
				
				future = completionService.submit(callable);
				futures.add(future);
			}
			
			/**
			 * 在主线程中 使用 线程池启动任务，用多行代码启动多个任务队列的时候，
			 * 是按照顺序来执行的，要等上一个任务队列处理完毕再执行下一个
			 */
			
			/*
			 * 发生有完成的立即进行处理
			 */
			int count = 0;
			while (count < 20)
			{
				future = completionService.poll();
				if (null == future)
				{
					//System.out.println("没有完成任务");
				} else
				{
					System.out.println("完成一个任务，结果: " + future.get());
					count++;
				}
			}
			
			//Thread.sleep(70 * 1000);
			// 关闭线程服务
			executorService.shutdown();

		} catch (Exception e) {
			log.error("testExecuteTaskCompletion =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConcurrency() {
		try {
			
			
		} catch (Exception e) {
			log.error("testConcurrency =====> ", e);
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
			System.out.println(Long.MAX_VALUE);
			
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
