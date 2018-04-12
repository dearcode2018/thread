/**
  * @filename RaceConditonExample.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.example;

 /**
 * @type RaceConditonExample
 * @description 
 * @author qianye.zheng
 */
public class RaceConditonExample
{
	/* 修复前的声明 */
	//private static boolean done = false;

	/*
	 * volatile 声明告诉JIT编译器不要对该变量做优化，volatile关键字警告编译器该变量可能会
	 * 被某个线程更改，所以任何对该变量的读写访问都需要忽略本地cache并直接对内存进行操作
	 * 
	 * 
	 * 分析: 虽然加上volatile会解决变量不一致问题，但是由于线程每次访问该变量都要跨越内存栅栏，
	 * 最终导致程序性能下降.
	 * 		在多个字段被多个线程并发访问的场景下，由于针对每个volatile字段的访问都是各自独立处理的，
	 * 并且也无法将这些访问统一协调成一次访问，所以volatile关键字无法保证整体操作的原子性。
	 * 该问题导致的后果是，线程可能对某些字段只能看到其中间结果，而对另一些变量看到的是最终结果.
	 * 
	 * 解决多个字段声明volatile问题，可以屏蔽对变量的直接访问，改为通过同步的getter和setter方法来
	 * 进行: public boolean synchronized boolean getXx(){}
	 * 关键字synchronized 是为数不多的几个可以令线程在进入和离开同步区块都跨越内存栅栏的原语之一.
	 * 所以，如果多个线程在相同的实例对象上进行同步并且先申请到对象锁的线程完成了对象实例的操作，
	 * 则后面申请到对象锁的线程将肯定可以看到前面完成操作的线程所做的变更.
	 * 
	 * 
	 *  
	 * 
	 * 
	 */
	
	/**
	 * 内存栅栏(Memory Barrier) 就是从本地或工作内存到主存之间的拷贝动作. (本地/工作内存 ---> 主存)
	 * 仅当写操作线程先跨越内存栅栏而读线程后跨越内存栅栏的情况下，写操作线程所做的变更才对其他
	 * 线程可见，该特性有助于内存边界动作的发生.
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	/* 修复后的声明 */
	private static volatile boolean done = false;	
	
	
	/**
	 * 内存栅栏
	 * 主线程对变量done的变更对新创建出来的线程不可见，造成这个现象的首要原因是 JIT编译器可能对新线程
	 * 代码里的while循环进行了优化，并因此导致新线程在线程上下文	中无法看到变量done的变化.
	 * 此外，新线程可能只会从其寄存器或本地cache中读取变量done的值，而不是每次都跑去速度更慢的内存去
	 * 操作。基于上述原因，新线程就无法看到主线程对变量done的变更了.
	 * 若要修复次问题，只需要将变量done声明为volatile(不稳定的).
	 * 
	 */
	
	/**
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		Thread t = new Thread(new Runnable() {
			/**
			 * @description 
			 * @author qianye.zheng
			 */
			@Override
			public void run()
			{
				/**
				 * Windows 32位默认以client模式运行，
				 * 当前机器是Windows 7 64位而默认以server模式运行，如果在run方法中写打印输出到控制台的代码
				 * 则会启动切换为client模式, client模式能检测得到done变量，可以结束程序，而server模式则不会结束.
				 * 
				 * 这种现象是由于JIT(Just In Time)编译器优化所导致的.
				 * 
				 */
				int i = 0;
				while (!done)
				{
					i++;
					//System.out.println("RaceConditonExample.main()");
				}
				System.out.println("Done!");
			}
		});
		t.start();
		System.out.println("OS: " + System.getProperty("os.name"));
		// 主线程休眠
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		done = true;
		System.out.println("flag done set to true");
	}

}
