/**
 * EggConsumer.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.thread.eg;

/**
 * EggConsumer
 * 描述: 
 * @author  qye.zheng
 */
public final class EggConsumer implements Runnable
{

	//
	private EggPlate eggPlate;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public EggConsumer()
	{
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param eggPlate
	 */
	public EggConsumer(EggPlate eggPlate)
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
			// 调用鸡蛋盘子 消费方法
			final Object egg = eggPlate.getEgg();
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
