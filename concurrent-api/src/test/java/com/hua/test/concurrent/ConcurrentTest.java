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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Ignore;
import org.junit.Test;

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
	public void testConcurrency() {
		try {
			
			
		} catch (Exception e) {
			log.error("testConcurrency =====> ", e);
		}
	}	
	
	/**
	 * 使用同步集合时，如果在修改的同时进行遍历，则会抛出ConcurrentModificationException异常，
	 * 究其本质，是由于在遍历的时候线程持有该集合的互斥锁(悲观锁).
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSynchronizedCollection() {
		try {
			List<String> list = new ArrayList<String>();
			//List<String> list = Collections.synchronizedList(new ArrayList<String>());
			//Vector<String> list = new Vector<String>();
			list.add("2");
			list.add("c");
			list.add("a");
			list.add("c");
			list.add("d");
			list.add("2d3d");
			ExecutorService executorService = Executors.newFixedThreadPool(20);
			List<Callable<String>> tasks = new ArrayList<Callable<String>>();
			tasks.add(new Callable<String>() {
				/**
				 * @description 
				 * @return
				 * @throws Exception
				 * @author qianye.zheng
				 */
				@Override
				public String call() throws Exception
				{
					list.add("xl93894");
					log.info("call1 =====> " + list.get(0));
					
					return null;
				}
			});
			
			tasks.add(new Callable<String>() {
				/**
				 * @description 
				 * @return
				 * @throws Exception
				 * @author qianye.zheng
				 */
				@Override
				public String call() throws Exception
				{
					list.add("9809sd");
					log.info("call2 =====> " + list.get(1));
					
					return null;
				}
			});
			
			
			executorService.invokeAll(tasks);
			
			
		} catch (Exception e) {
			log.error("testSynchronizedCollection =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCollection() {
		try {
			List<String> list = new ArrayList<String>();
			//Vector<String> list = new Vector<String>();
			list.add("2");
			list.add("c");
			list.add("a");
			list.add("c");
			list.add("d");
			list.add("2d3d");
			
			for (String e : list)
			{
				log.info("testCollection =====> " + e);
				if ("c".equals(e))
				{
					list.remove(e);
				}
			}
			
		} catch (Exception e) {
			log.error("testCollection =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSynchronizedMap() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("1", "a");
			map.put("2", "d");
			map.put("3", "f");
			map.put("c", "g");
			map.put("g", "o");
			Set<String> keySet = map.keySet();
			/*
			 * 
			 * java.util.ConcurrentModificationException
			 */
			for (String e : keySet)
			{
				if ("c".equals(e))
				{
					map.remove(e);
				}
				log.info("testSynchronizedMap =====> " + map.get(e));
			}
			
		} catch (Exception e) {
			log.error("testSynchronizedMap =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConcurrentMap() {
		try {
			ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
			map.put("1", "a");
			map.put("2", "d");
			map.put("3", "f");
			map.put("c", "g");
			map.put("g", "o");
			Set<String> keySet = map.keySet();
			/*
			 * 并发map不会抛同步修改异常
			 * 
			 * java.util.ConcurrentModificationException
			 */
			for (String e : keySet)
			{
				if ("c".equals(e))
				{
					map.remove(e);
				}
				log.info("testConcurrentMap =====> " + map.get(e));
			}
			
		} catch (Exception e) {
			log.error("testConcurrentMap =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testConcurrentCollection() {
		try {
			List<String> list = new ArrayList<String>();
			//Vector<String> list = new Vector<String>();
			list.add("2");
			list.add("c");
			list.add("a");
			list.add("c");
			list.add("d");
			list.add("2d3d");
			
			for (String e : list)
			{
				log.info("testCollection =====> " + e);
				if ("c".equals(e))
				{
					list.remove(e);
				}
			}
			
		} catch (Exception e) {
			log.error("testCollection =====> ", e);
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
