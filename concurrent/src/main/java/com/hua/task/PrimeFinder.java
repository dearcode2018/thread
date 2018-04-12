/**
  * @filename PrimeFinder.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

 /**
 * @type PrimeFinder
 * @description 
 * @author qianye.zheng
 */
public class PrimeFinder
{
	
	/**
	 * 
	 * @description 是否是素数
	 * @param number
	 * @return
	 * @author qianye.zheng
	 */
	private boolean isPrime(final int number)
	{
		if (number <= 1)
		{
			return false;
		}
		
		for (int i = 2; i <= Math.sqrt(number); i++)
		{
			if (0 == (number % i))
			{
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @description 统计区间内素数的总数
	 * @param lower
	 * @param upper
	 * @return
	 * @author qianye.zheng
	 */
	private int countPrimesInRange(final int lower, final int upper)
	{
		int total = 0;
		for (int i = lower; i <= upper; i++)
		{
			if (isPrime(i))
			{
				total++;
			}
		}
		
		return total;
	}
	
	/**
	 * 
	 * @description 统计某个数字一下的素数个数，并输出计算时间
	 * @param number
	 * @author qianye.zheng
	 */
	public void timeAndCompute(final int number)
	{
		final long start = System.nanoTime();
		final int numberOfPrimes = countPrimesInRange(1, number);
		final long end = System.nanoTime();
		
		System.out.printf("Number of primes under %d is %d\n", number, numberOfPrimes);
		
		System.out.println("time seconds token is " + (end - start) / 1.0e9);
	}
	
	
}
