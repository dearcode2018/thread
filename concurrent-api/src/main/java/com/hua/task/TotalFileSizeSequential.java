/**
  * @filename TotalFileSizeSequential.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.io.File;

/**
 * @type TotalFileSizeSequential
 * @description 顺序统计目录大小
 * @author qianye.zheng
 */
public class TotalFileSizeSequential
{
	
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public static final long getTotalSizeOfFilesInDir(final File file)
	{
		if (file.isFile())
		{
			return file.length();
		}
		final File[] children = file.listFiles();
		long total = 0L;
		if (null != children)
		{
			for (final File child : children)
			{
				total += getTotalSizeOfFilesInDir(child);
			}
		}
		
		return total;
	}
}
