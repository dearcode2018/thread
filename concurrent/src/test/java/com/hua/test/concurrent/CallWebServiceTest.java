/**
 * 描述: 
 * CallWebServiceTest.java
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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hua.bean.Pager;
import com.hua.task.SearchUserTaskCallable;
import com.hua.task.SearchUserTaskRunnable;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CallWebServiceTest
 */
public final class CallWebServiceTest extends BaseTest {
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallWebserviceConcurrent001() {
		Future<?> future = null;
		String result = "custom definition";
		try {
			/*
			 * 创建一个持有20个线程的线程池
			 */
			ExecutorService executorService = Executors.newScheduledThreadPool(20);
			Integer pageSize = 50;
			Integer currentPage = 1;
			SearchUserTaskRunnable runnable = null;
			/**
			 * 取20页的数据，每个线程去取一页的数据
			 */
			for (int i = 0; i < 20; i++)
			{
				currentPage = i + 1;
				runnable = new SearchUserTaskRunnable(pageSize);
				runnable.setCurrentPage(currentPage);
				/**
				 * 1.执行一个可运行的任务，没有返回值
				 * 即在主线程中无法获得任务的返回值
				 */
				//executorService.execute(runnable);
				
				/**
				 * 2.不携带返回值的Future对象
				 * 通过该对象可以知悉任务执行情况: 是否取消执行、是否执行结束、是否取消该任务
				 */
				//future = executorService.submit(runnable);
				
				/**
				 * 3.携带自定义返回值的Future对象
				 * 任务成功执行之后，返回自定义的结果，可以通过结果来判断任务的执行是否成功.
				 * 通过该对象可以知悉任务执行情况: 是否取消执行、是否执行结束、是否取消该任务
				 * 
				 * 需要在任务中伪造异常，让某个任务不能成功执行完，从而观察返回结果
				 * 返回结果需要从 future.get()方法去获取
				 */
				future = executorService.submit(runnable, result);
			}
			
			Thread.sleep(100 * 1000);
			// 关闭线程服务
			executorService.shutdown();
			// 最后一个任务的执行情况 done只是表示完成状态，不代表是否成功完成
			System.out.println("done status = " + future.isDone());
			
		} catch (Exception e) {
			log.error("testCallWebserviceConcurrent001 =====> ", e);
		}
		try
		{
			/**
			 * 由于任务没有成功结束，因此返回值没有使用自定义的返回值，而是返回异常信息
			 */
			System.out.println("result = " + future.get().toString());
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		} catch (ExecutionException e)
		{
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
	public void testCallWebserviceConcurrent002() {
		Future<String> future = null;
		List<Future<String>> futures = new ArrayList<Future<String>>();
		try {
			/*
			 * 创建一个持有20个线程的线程池
			 */
			ExecutorService executorService = Executors.newScheduledThreadPool(20);
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
				future = executorService.submit(callable);
				futures.add(future);
			}
			
			/**
			 * 调用所有的
			 */
			//executorService.invokeAll(null, 10, TimeUnit.SECONDS);
			
			Thread.sleep(80 * 1000);
			// 关闭线程服务
			executorService.shutdown();
			
			// 批量输出结果
			for (final Future<String> f : futures)
			{
				System.out.println(f.get());
			}
			
		} catch (Exception e) {
			log.error("testCallWebserviceConcurrent002 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallWebserviceConcurrent003() {
		List<Future<String>> futures = null;
		List<Callable<String>> callables = new ArrayList<Callable<String>>();
		try {
			/*
			 * 创建一个持有20个线程的线程池
			 */
			ExecutorService executorService = Executors.newScheduledThreadPool(20);
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
				callables.add(callable);
			}
			
			/**
			 * 批量调用所有的，定义超时时间，超时时间是所有任务的总超时时间，不是单个
			 */
			futures = executorService.invokeAll(callables, 30, TimeUnit.SECONDS);
			
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
			log.info("testCallWebserviceConcurrent003 =====> count = " + count);
		} catch (Exception e) {
			log.error("testCallWebserviceConcurrent003 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCallWebService001() {
		try {
			// 构造请求对象
			final StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
			final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			messageConverters.add(messageConverter);
			final RestTemplate request = new RestTemplate(messageConverters);
			final MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Content-Type", "application/json; charset=UTF-8");
			headers.add("Accept", "application/json; charset=UTF-8");
			headers.add("APP-ID", "100001");
			headers.add("X-AUTH-HEADER", "AFKJLKWEOIK02LKJAM23OKASK");
			String url = "http://10.237.151.181:8085/hotel-manage-platform-api/rest/user/search/v1";
			Pager pager = new Pager();
			pager.setPageSize(100);
			pager.setCurrentPage(1);
			String body = JacksonUtil.writeAsString(pager);
			HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
			ResponseEntity<String> entity = request.exchange(url, HttpMethod.POST, requestEntity, String.class);
			String result = entity.getBody().toString();
			
			log.info("testCallWebService001 =====> \n" + result);
			
		} catch (Exception e) {
			log.error("testCallWebService001 =====> ", e);
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
