/**
 * 描述: 
 * ThreadBasicTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.thread;

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

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ThreadBasicTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class ThreadBasicTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testThreadBasic() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			
			
			Runnable target = () -> System.out.println("do something...");
			//Thread t1 = new Thread(target, "线程SunDay");
			
			Thread t1 = new Thread(target);
			t1.start();
			//Callable<V>
			System.out.println("isAlive = " + t1.isAlive());
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			t1.interrupt();
			System.out.println("isInterrupted = " + t1.isInterrupted());
			// 当前线程组的活跃线程
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
			
		} catch (Exception e) {
			log.error("testThreadBasic =====> ", e);
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
	public void testThreadGroup() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			
			
			Runnable target = () -> System.out.println("do something...");
			//Thread t1 = new Thread(target, "线程SunDay");
			
			Thread t1 = new Thread(target);
			// 主线程创建的线程，也在同一个组
			assertTrue(mainThread.getThreadGroup() == t1.getThreadGroup());
			
			// 当前线程组的活跃线程
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testThreadGroup =====> ", e);
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
	public void testGetAllStackTraces() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			//mainThread.getPriority();
			
			Runnable target = () -> System.out.println("do something...");
			//Thread t1 = new Thread(target, "线程SunDay");
			
			Thread t1 = new Thread(target);
			t1.start();

			//t1.interrupt();
			//System.out.println("isInterrupted = " + t1.isInterrupted());
			Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
			threadMap.entrySet().forEach(x -> {
				Thread t = x.getKey();
				StackTraceElement[] ste = x.getValue();
				for (StackTraceElement e : ste) {
					System.out.println("name = " + t.getName() + ", className = " + e.getClassName() + ", methodName = " + e.getMethodName());
				}
			});
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
			
		} catch (Exception e) {
			log.error("testGetAllStackTraces =====> ", e);
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
	public void testHoldsLock() {
		try {
			
			System.out.println(Thread.holdsLock(this));
			
		} catch (Exception e) {
			log.error("testHoldsLock =====> ", e);
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
	public void testDumpStack() {
		try {
			Thread.dumpStack();
			
		} catch (Exception e) {
			log.error("testDumpStack =====> ", e);
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
	public void testThreadState() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				// RUNNABLE
				System.out.println("t1ThreadState3 = " +Thread.currentThread().getState());
				System.out.println("do something...");
			};
			//Thread t1 = new Thread(target, "线程SunDay");
			
			Thread t1 = new Thread(target);
			// NEW
			System.out.println("t1ThreadState1 = " + t1.getState());
			
			t1.start();
			// RUNNABLE
			System.out.println("t1ThreadState2 = " + t1.getState());
			t1.join();
			
			// TERMINATED
			System.out.println("t1ThreadState4 = " + t1.getState());
			//Callable<V>
			//System.out.println("isAlive = " + t1.isAlive());
			//System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			t1.interrupt();
			
			//t1.wait(5000);
			
			//System.out.println("isInterrupted = " + t1.isInterrupted());
			
			//System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
			
		} catch (Exception e) {
			log.error("testThreadState =====> ", e);
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
	public void testInterrupt() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) { // 中断 休眠/阻塞/等待
						e.printStackTrace();
					}
					System.out.println("do something...");
				}
			};
			
			Thread t1 = new Thread(target);
			
			t1.start();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			t1.interrupt();
			
			System.out.println("isInterrupted = " + t1.isInterrupted());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testInterrupt =====> ", e);
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
	public void testInterrupt2() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				while (true) {
					System.out.println("do something...");
					/*
					 * 暗示处理器，当前线程可以让出执行机会，处理器不一定采纳
					 */
					Thread.yield();
				}
			};
			
			Thread t1 = new Thread(target);
			
			t1.start();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			t1.interrupt();
			
			System.out.println("isInterrupted = " + t1.isInterrupted());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testInterrupt2 =====> ", e);
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
	public void testInterrupt3() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			//System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				System.out.println("ThreadBasicTest.testInterrupt3()");
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			
			Thread t1 = new Thread(target);
			
			t1.start();
			System.out.println("status1 = " + t1.getState());
			t1.interrupt();
			System.out.println("status2 = " + t1.getState());
			System.out.println("isInterrupted = " + t1.isInterrupted());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testInterrupt3 =====> ", e);
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
	public void testObjectWait() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			Object obj = new Object();
			
			
			
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			// 上游工作
			Runnable upWork = () -> {
				while (true) {
					int i = 50;
					do {
						System.out.println("up do something...");
						i--;
						try {
							TimeUnit.MILLISECONDS.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (i > 0);
					try {
						obj.wait();
					} catch (InterruptedException e) { // 中断，不再等待
						e.printStackTrace();
					}
				}
			};
			
			// 下游工作
			Runnable downWork = () -> {
				try {
					obj.wait();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while (true) {
					int i = 10;
					do {
						System.out.println("down do something...");
						i--;
						try {
							TimeUnit.MILLISECONDS.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (i > 0);
					/*
					 * try { obj.notify(); } catch (InterruptedException e) { //
					 * 中断，不再等待 e.printStackTrace(); }
					 */
				}
			};
			
			
			
		} catch (Exception e) {
			log.error("testObjectWait =====> ", e);
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
	public void testDeamon() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				while (true) {
					// 只有由守护线程创建的还是守护线程，普通线程创建的则为普通线程
					Thread tInner = new Thread();
					System.out.println(Thread.currentThread().getName() + ", deamon = " + tInner.isDaemon());
					
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) { // 中断 休眠/阻塞/等待
						e.printStackTrace();
					}
					System.out.println("do something...");
				}
			};
			
			Thread t1 = new Thread(target);
			// 在start()之前设置
			t1.setDaemon(true);
			
			t1.start();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			t1.interrupt();
			
			
			Thread t2 = new Thread(target);
			// 在start()之前设置
			t2.setDaemon(false);
			
			t2.start();
			
			
			System.out.println("isInterrupted = " + t1.isInterrupted());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testDeamon =====> ", e);
		}
	}
	
	
	/**
	 * join的常用场景
	 * 在主线程中运行一一个异步模块，不希望主线程提前结束，
	 * 就可以采用join的方式
	 * 
	 * 可以设置等待超时时间，超过指定时间，就不在等待下去了.
	 * 
	 * 线程对象A 构造了线程对象B，并且线程对象B调用join()，则线程对象A会一直等待线程B执行完成才继续执行或者等待指定时间
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testJoin() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			Runnable target = () -> {
				System.out.println("do something...");
				System.out.println("mainThread state = " + mainThread.getState());
			};
			
			Thread t1 = new Thread(target);
			t1.start();
			/*
			 * join代表容器，容器外的要等t1执行结束才销毁
			 * 若多个线程同时进入一个容器，则要等指定的线程结束，容器才销毁
			 * join()要在start()之后
			 */
			t1.join();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testJoin =====> ", e);
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
	public void testJoin2() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				System.out.println("do something...");
			};
			
			Thread t1 = new Thread(target);
			Thread t2 = new Thread(target);
			
			t1.start();
			/*
			 * join代表容器，容器外的要等t1执行结束才销毁
			 * 若多个线程同时进入一个容器，则要等指定的线程结束，容器才销毁
			 */
			t1.join();
			t2.start();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testJoin2 =====> ", e);
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
	public void testJoin3() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				System.out.println("do something...");
				Thread t = Thread.currentThread();
				if ("线程2".equals(t.getName())) {
					System.out.println("线程2");
				}
			};
			
			Thread t1 = new Thread(target, "线程1");
			Thread t2 = new Thread(target, "线程2");
			
			t1.start();
			t2.start();
			/*
			 * join代表容器，容器外的要等t1执行结束才销毁
			 * 若多个线程同时进入一个容器，则要等指定的线程结束，容器才销毁
			 */
			t1.join();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testJoin3 =====> ", e);
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
	public void testJoin4() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			System.out.println("mainThreadState = " + mainThread.getState());
			
			Runnable target = () -> {
				System.out.println("do something...");
				Thread t = Thread.currentThread();
				if ("线程2".equals(t.getName())) {
					System.out.println("线程2");
				}
			};
			
			Thread t1 = new Thread(target, "线程1");
			Thread t2 = new Thread(target, "线程2");
			
			t1.start();
			t2.start();
			/*
			 * join代表容器，容器外的要等t1执行结束才销毁
			 * 若多个线程同时进入一个容器，则要等指定的线程结束，容器才销毁
			 */
			// 主线程等待t1/t2执行完成后 再继续执行，而t1/t2是并行的，t1/t2和主线程是串行的
			t1.join();
			t2.join();
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testJoin4 =====> ", e);
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
	public void testJoin5() {
		try {
			Thread mainThread = Thread.currentThread();
			System.out.println("主线程: id = " + mainThread.getId() + ", name = " + mainThread.getName() + ", priority = " + mainThread.getPriority());
			
			/*
			 * 优先级，
			 * MIN_PRIORITY
			 * NORM_PRIORITY
			 * MAX_PRIORITY
			 */
			mainThread.getPriority();
			Runnable target = () -> {
				System.out.println("do something...");
				// TIMED_WAITING
				System.out.println("mainThread state = " + mainThread.getState());
				Thread t = Thread.currentThread();
				if ("线程2".equals(t.getName())) {
					System.out.println("线程2");
				}
			};
			
			Thread t1 = new Thread(target, "线程1");
			
			t1.start();
			/*
			 * join代表容器，容器外的要等t1执行结束才销毁
			 * 若多个线程同时进入一个容器，则要等指定的线程结束，容器才销毁
			 */
			/*
			 * 父线程(创建t1的线程，这里是主线程) 阻塞指定时间，再继续往下执行
			 */
			t1.join(2 * 1000);
			System.out.println("id = " + t1.getId() + ", name = " + t1.getName() + ", priority = " + t1.getPriority());
			
			System.out.println("threadGroupActiveCount = " + Thread.activeCount());
			
		} catch (Exception e) {
			log.error("testJoin5 =====> ", e);
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
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}
