/**
 * 描述: 
 * VolatileTest.java
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

import com.hua.bean.Counter;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * VolatileTest
 */
public final class VolatileTest extends BaseTest {

	/**
	 * 每一个线程运行时都有一个线程栈，线程栈保存了线程运行时变量信息值。
	 * 当一个线程访问某一个对象的值的时候，首先通过对象的引用找到堆内存的变量值，然后把
	 * 堆内存变量的具体值load到线程栈中，建立一个副本，之后该线程就不再和对象在堆内存变量值
	 * 有任何关系，而是直接修改副本变量的值，修改完成之后的某一时刻(线程退出之前)，自动把变量
	 * 副本值写到对象的堆内存中.
	 * 
	 * volatile 修饰变量，可以确保一个线程在访问该变量之前，都会去获取其最新的值.
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNoVolatile() {
		try {
			// 同时启动 1000 个线程，去进行 i++ 计算，看看实际结果
			for (int i = 0; i < 1000; i++)
			{
				new Thread(new Runnable() {
					/**
					 * @description 
					 * @author qianye.zheng
					 */
					@Override
					public void run()
					{
						Counter.incCountNoVolatile();
					}
				}).start();
			}
			// 每次运行结果都不同，可能为 1000
			System.out.println("countNoVolatile = " + Counter.countNoVolatile);
		} catch (Exception e) {
			log.error("testNoVolatile =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWithVolatile() {
		try {
			// 同时启动 1000 个线程，去进行 i++ 计算，看看实际结果
			for (int i = 0; i < 1000; i++)
			{
				new Thread(new Runnable() {
					/**
					 * @description 
					 * @author qianye.zheng
					 */
					@Override
					public void run()
					{
						Counter.incCountWithVolatile();
					}
				}).start();
			}
			// 每次运行结果都不同，可能为 1000
			System.out.println("countWithVolatile = " + Counter.countWithVolatile);
			
		} catch (Exception e) {
			log.error("testWithVolatile =====> ", e);
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
