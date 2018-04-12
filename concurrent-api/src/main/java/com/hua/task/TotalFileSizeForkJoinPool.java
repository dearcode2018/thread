/**
  * @filename TotalFileSizeForkJoinPool.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @type TotalFileSizeForkJoinPool
 * @description 
 * @author qianye.zheng
 */
public class TotalFileSizeForkJoinPool
{
	
	/**
	 * 
	 * @type FileSizeFinder
	 * @description 
	 * @author qianye.zheng
	 */
	public static final class FileSizeFinder extends RecursiveTask<Long>
	{
		private static final long serialVersionUID = 3462208154160939116L;
		
		private final File file;
		
		/**
		 * @description 构造方法
		 * @param file
		 * @author qianye.zheng
		 */
		public FileSizeFinder(File file)
		{
			super();
			this.file = file;
		}

		/**
		 * @description 
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		protected Long compute()
		{
			Long size = 0L;
			if (file.isFile())
			{
				size = file.length();
			} else
			{
				final File[] children = file.listFiles();
				if (null != children)
				{
					final List<ForkJoinTask<Long>> tasks = new ArrayList<ForkJoinTask<Long>>();
					for (final File child : children)
					{
						if (child.isFile())
						{
							size += child.length();
						} else
						{
							tasks.add(new FileSizeFinder(child));
						}
					}
					for (final ForkJoinTask<Long> task : invokeAll(tasks)) 
					{
						size += task.join();
					}
				}
			}
			
			return size;
		}
	}
	
}
