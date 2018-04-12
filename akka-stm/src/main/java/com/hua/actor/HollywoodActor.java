/**
  * @filename HollywoodActor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * @type HollywoodActor
 * @description 
 * @author qianye.zheng
 */
public class HollywoodActor extends UntypedActor
{

	/**
	 * @description 
	 * @param role
	 * @throws Exception
	 * @author qianye.zheng
	 */
	@Override
	public void onReceive(Object role) throws Exception
	{	
		System.out.println("Playing " + role + " from Thread " + Thread.currentThread().getName());
	}	



}
