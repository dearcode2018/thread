/**
  * @filename TotalFileSizeConcurrent.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @type TotalFileSizeConcurrent
 * @description 
 * @author qianye.zheng
 */
public class TotalFileSizeConcurrent
{
	
	/**
	 * 
	 * @type SubDirectoriesAndSize
	 * @description 子目录和当前目录下所有文件的大小
	 * @author qianye.zheng
	 */
	class SubDirectoriesAndSize 
	{
		private Long size;
		
		private List<File> subDirectories;
		
		/**
		 * 
		 * @description 构造方法
		 * @param size
		 * @param theSubDirs
		 * @author qianye.zheng
		 */
		public SubDirectoriesAndSize(final Long size, final List<File> subDirectories)
		{
			this.size = size;
			this.subDirectories = subDirectories;
		}

		/**
		* @return the size
		*/
		public final Long getSize()
		{
			return size;
		}

		/**
		* @param size the size to set
		*/
		public final void setSize(Long size)
		{
			this.size = size;
		}

		/**
		* @return the subDirectories
		*/
		public final List<File> getSubDirectories()
		{
			return subDirectories;
		}

		/**
		* @param subDirectories the subDirectories to set
		*/
		public final void setSubDirectories(List<File> subDirectories)
		{
			this.subDirectories = subDirectories;
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	private SubDirectoriesAndSize getTotalAndSubDirs(final File file)
	{
		Long total = 0L;
		final List<File> subDirectories = new ArrayList<File>();
		if (file.isDirectory())
		{
			final File[] children = file.listFiles();
			if (null != children)
			{
				for (final File child : children)
				{
					if (child.isFile())
					{
						total += child.length();
					} else
					{
						subDirectories.add(child);
					}
				}
			}
		}
		
		return new SubDirectoriesAndSize(total, subDirectories);
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public Long getTotalSizeOfFilesInDir(final File file)
	{
		Long total = 0L;
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		try
		{
			final List<File> directories = new ArrayList<File>();
			directories.add(file);
			while (!directories.isEmpty())
			{
				final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
				for (final File f : directories)
				{
					partialResults.add(executorService.submit(new Callable<SubDirectoriesAndSize>(){
						
						/**
						 * @description 
						 * @return
						 * @throws Exception
						 * @author qianye.zheng
						 */
						@Override
						public SubDirectoriesAndSize call() throws Exception
						{
							return getTotalAndSubDirs(f);
						}
					}));
				}
				directories.clear();
				for (final Future<SubDirectoriesAndSize> future : partialResults)
				{
					// 在设置的超时时间内获取结果，可以在调试中及时发生问题防止系统奔溃
					final SubDirectoriesAndSize subDirectoriesAndSize = future.get(100, TimeUnit.SECONDS);
					directories.addAll(subDirectoriesAndSize.getSubDirectories());
					total += subDirectoriesAndSize.getSize();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		executorService.isShutdown();
		
		return total;
	}
}
