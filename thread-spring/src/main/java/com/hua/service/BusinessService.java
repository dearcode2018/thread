/**
  * @filename BusinessService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

 /**
 * @type BusinessService
 * @description 
 * @author qianye.zheng
 */
public interface BusinessService extends CoreService
{
	
	/**
	 * 
	 * @description 
	 * @param times 次数
	 * @author qianye.zheng
	 */
	public void doSomething(int times);
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	public long getConsumeTs();
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	public boolean finished();
}
