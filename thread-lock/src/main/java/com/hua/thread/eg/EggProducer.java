/**
 * EggProducer.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.eg;

/**
 * EggProducer
 * 描述: 鸡蛋生产者
 * @author  qye.zheng
 */
public final class EggProducer implements Runnable
{
	//
	private EggPlate eggPlate;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public EggProducer()
	{
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param eggPlate
	 */
	public EggProducer(EggPlate eggPlate)
	{
		super();
		this.eggPlate = eggPlate;
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 */
	@Override
	public void run()
	{
		// 加上 while (true) 循环，避免线程早于主线程结束
		while (true)
		{
			final Object egg = new Object();
			// 调用鸡蛋盘子 放置鸡蛋方法
			eggPlate.putEgg(egg);
		}
	}

	/**
	 * @return the eggPlate
	 */
	public EggPlate getEggPlate()
	{
		return eggPlate;
	}

	/**
	 * @param eggPlate the eggPlate to set
	 */
	public void setEggPlate(EggPlate eggPlate)
	{
		this.eggPlate = eggPlate;
	}

}
