/**
 * 描述: 
 * ThreadPoolExecutorTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.pool;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.task.MyTask;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ThreadPoolExecutorTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class ThreadPoolExecutorTest extends BaseTest {

	
	/*
	*运行状态提供了主生命周期控制，其值为: 
		1) 运行: 接受新任务并处理排队的任务
		2) 关闭: 不接受新任务，但处理排队的任务
		3) 停止: 不接受新任务，不处理排队的任务，中断正在进行的任务
		4) 整理: 所有任务都已终止，workerCount为零，转换为状态整理的线程
		5) 已终止: 终止已完成
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testThreadPoolExecutor() {
		try {
			/**
			 * 1) 当线程池中的任务数大于corePoolSize时，新增的任务会放入队列中
			 * 
			 * 2) 当存队列满了之后，便创建新的线程
			 * 
			 * 3) 当创建的最大线程数超过maxPoolSize时，就会执行拒绝策略，默认是AbortPolicy
			 * 
			 */
			int corePoolSize = 5;
			int maxPoolSize = 12;
			int task = 12;
			task = 17;
			BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(3);
			ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 2, TimeUnit.SECONDS, blockQueue);
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
			// 允许核心线程 空闲超时，将使用和非核心线程相同的驱逐策略
			executor.allowCoreThreadTimeOut(true);
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
			log.error("testThreadPoolExecutor =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testExecuteRunnable() {
		try {
			ExecutorService executor = executorService(1, 20, 60L);
			ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;
			//System.out.println("activeCount = " + tpe.getActiveCount());
			// 重启1个线程
			//System.out.println(tpe.prestartCoreThread());
			// 重启所有核心线程
			//System.out.println(tpe.prestartAllCoreThreads());
			//TimeUnit.MILLISECONDS.sleep(100);
			//System.out.println("activeCount = " + tpe.getActiveCount());
			System.out.println("线程池中线程数目: " + tpe.getPoolSize());
			// 提交任务
			executor.execute(() -> {
				// 默认是通过 Executors.DefaultThreadFactory.newThread() 构造线程
				System.out.println("thread name = " + Thread.currentThread().getName());
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			System.out.println("线程池中线程数目: " + tpe.getPoolSize());
			
			// 重启所有核心线程
			System.out.println(tpe.prestartAllCoreThreads());
			
			
			executor.execute(() -> {
				// 默认是通过 Executors.DefaultThreadFactory.newThread() 构造线程
				System.out.println("thread name = " + Thread.currentThread().getName());
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			
		} catch (Exception e) {
			log.error("testExecuteRunnable =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSubmitRunnable() {
		try {
			ExecutorService executor = executorService(2, 20, 60L);
			// 提交任务
			Future<?> future = executor.submit(() -> {
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			
			/*
			 * 阻塞，知道任务执行完毕
			 * 返回为空
			 */
			Object result = future.get();
			assertTrue(null == result);
		} catch (Exception e) {
			log.error("testSubmitRunnable =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSubmitRunnableWithTimeout() {
		try {
			ExecutorService executor = executorService(2, 20, 60L);
			// 提交任务
			Future<?> future = executor.submit(() -> {
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			
			/*
			 * 指定最大阻塞时间，超过则抛出 TimeoutException
			 * 返回为空
			 */
			Object result = future.get(5, TimeUnit.SECONDS);
			System.out.println("current thread continue do");
			assertTrue(null == result);
		} catch (Exception e) {
			log.error("testSubmitRunnableWithTimeout =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSubmitRunnableWithReturn() {
		try {
			ExecutorService executor = executorService(2, 20, 60L);
			// 提交任务
			Future<?> future = executor.submit(() -> {
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
				throw new RuntimeException("dd");
			}, "return value");
			
			/*
			 * 指定最大阻塞时间，超过则抛出 TimeoutException
			 * 返回为空
			 */
			Object result = future.get(5, TimeUnit.SECONDS);
			System.out.println("current thread continue do");
			assertTrue(null != result);
			System.out.println("return = " + result);
		} catch (Exception e) {
			log.error("testSubmitRunnableWithReturn =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSubmitCallable() {
		try {
			ExecutorService executor = executorService(2, 20, 5L);
			// 提交任务
			Future<String> future = executor.submit(() -> {
				// 默认是通过 Executors.DefaultThreadFactory.newThread() 构造线程
				System.out.println("thread name = " + Thread.currentThread().getName());
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
				
				return "from callable";
			});
			
			/*
			 * 阻塞，知道任务执行完毕
			 */
			Object result = future.get();
			assertTrue(null != result);
			System.out.println("return = " + result);
		} catch (Exception e) {
			log.error("testSubmitRunnable =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testKeepAlive() {
		try {
			ExecutorService executor = executorService(2, 20, 3L);
			ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;
			tpe.allowCoreThreadTimeOut(true);
			//System.out.println("activeCount = " + tpe.getActiveCount());
			// 重启1个线程
			//System.out.println(tpe.prestartCoreThread());
			// 重启所有核心线程
			//System.out.println(tpe.prestartAllCoreThreads());
			//TimeUnit.MILLISECONDS.sleep(100);
			//System.out.println("activeCount = " + tpe.getActiveCount());
			System.out.println("线程池中线程数目: " + tpe.getPoolSize());
			// 提交任务
			executor.execute(() -> {
				// 默认是通过 Executors.DefaultThreadFactory.newThread() 构造线程
				System.out.println("thread name = " + Thread.currentThread().getName());
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			System.out.println("线程池中线程数目: " + tpe.getPoolSize());
			
			// 重启所有核心线程
			//System.out.println(tpe.prestartAllCoreThreads());
			
			executor.execute(() -> {
				// 默认是通过 Executors.DefaultThreadFactory.newThread() 构造线程
				System.out.println("thread name = " + Thread.currentThread().getName());
				System.out.println("ThreadPoolExecutorTest.testSubmitRunnable()");
			});
			
		} catch (Exception e) {
			log.error("testKeepAlive =====> ", e);
		}
	}	
	
	/**
	 * 
	 * @description 
	 * @param poolSize 池大小 (核心数/总数)
	 * @param queueSize 队列大小
	 * @param idleSecond 空闲时间，单位: 秒
	 * @return
	 * @author qianye.zheng
	 */
	private ExecutorService executorService(final int poolSize, final int queueSize, final long idleSecond) {
		final BlockingQueue<Runnable> blockQueue = new ArrayBlockingQueue<Runnable>(queueSize);
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize, idleSecond, TimeUnit.SECONDS, blockQueue);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		// 允许核心线程 空闲超时，将使用和非核心线程相同的驱逐策略
		executor.allowCoreThreadTimeOut(true);
		
		return executor;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLinkedBlockQueue() {
		try {
			/*
			 * 有/无界队列，可以不指定初始容量 (指定初始容量才受初始容量限制)
			 * 指定初始容量时为有界对象，否则为无界队列
			 */
			// 无界队列
			BlockingQueue<String> blockQueue = new LinkedBlockingQueue<>();
			// 有界队列
			//BlockingQueue<String> blockQueue = new LinkedBlockingQueue<>(2);
			blockQueue.offer("a");
			blockQueue.offer("b");
			// 指定队列大小时，超出队列大小，不再添加，offer不抛异常，add会抛异常
			blockQueue.offer("c");
			// 异常: java.lang.IllegalStateException: Queue full
			//blockQueue.add("d");
			
			System.out.println(blockQueue.size());
			while (!blockQueue.isEmpty()) {
				System.out.println(blockQueue.poll());
			}
		} catch (Exception e) {
			log.error("testLinkedBlockQueue=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testArrayBlockQueue() {
		try {
			// 有界队列，必须指定队列长度，受初始容量限制
			BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(2);
			blockQueue.offer("a");
			blockQueue.offer("b");
			// 超出队列大小，不再添加，offer不抛异常，add会抛异常
			//blockQueue.offer("c");
			// 异常: java.lang.IllegalStateException: Queue full
			//blockQueue.add("d");
			
			System.out.println(blockQueue.size());
			while (!blockQueue.isEmpty()) {
				System.out.println(blockQueue.poll());
			}
			
		} catch (Exception e) {
			log.error("testArrayBlockQueue=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
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
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
	@Test
	public void testSimple() {
		try {
			
			System.out.println(-1 << 29);
			System.out.println(0 << 29);
			System.out.println(1 << 29);
			System.out.println(2 << 29);
			System.out.println(3 << 29);
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
	}

}
