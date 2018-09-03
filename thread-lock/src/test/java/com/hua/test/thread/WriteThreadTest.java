/**
 * 描述: 
 * WriteThreadTest.java
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

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.thread.WriteService;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * WriteThreadTest
 */
public final class WriteThreadTest extends BaseTest {

	
	private WriteService writeService = new WriteService();
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWrite1001() {
		try {
			
			/**
			 * 在  writeService 的方法中打断点，然后观察，只能允许一个线程进入
			 */
			Thread t1 = new Thread(new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					writeService.write1("abcff");
				}
			});
			
			Thread t2 = new Thread(new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					writeService.write1("abcee");
				}
			});
			
			t1.start();
			
			t2.start();
			
			Thread.sleep(50 * 1000);
			
		} catch (Exception e) {
			log.error("testWrite1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWrite1002() {
		try {
			writeService.write1("abc");
			
		} catch (Exception e) {
			log.error("testWrite1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWrite2001() {
		try {
			
			/**
			 * 在  writeService 的方法中打断点，然后观察，只能允许一个线程进入
			 */
			Thread t1 = new Thread(new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					writeService.write2("abcff");
				}
			});
			Thread t2 = new Thread(new Runnable() {
				/**
				 * @description 
				 * @author qianye.zheng
				 */
				@Override
				public void run()
				{
					writeService.write2("abcee");
				}
			});
			
			t1.start();
			
			t2.start();
			
			Thread.sleep(50 * 1000);
			
		} catch (Exception e) {
			log.error("testWrite2001 =====> ", e);
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
