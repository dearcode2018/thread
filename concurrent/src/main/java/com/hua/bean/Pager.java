/**
* Pager.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.bean;


/**
 * 
 * @type Pager
 * @description 分页器
 * @param 
 * @author qianye.zheng
 */
public class Pager {
	
	
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	
	/* 页_大小 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	
	/* 当前页，从1开始 */
	private Integer currentPage = 1;
	
	/* 记录总条数 */
	private Integer dataSize = 0;
	
	/* 总页数 */
	private Integer totalPage;
	
	/*  */
	private boolean needPage = true;
	
	/* 分页数据 */
	private Object beanList;

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		if (pageSize <= 0)
		{
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the dataSize
	 */
	public Integer getDataSize() {
		return dataSize;
	}

	/**
	 * @param dataSize the dataSize to set
	 */
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * @return the needPage
	 */
	public boolean isNeedPage() {
		return needPage;
	}

	/**
	 * @param needPage the needPage to set
	 */
	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}

	/**
	 * @return the beanList
	 */
	public Object getBeanList() {
		return beanList;
	}

	/**
	 * @param beanList the beanList to set
	 */
	public void setBeanList(Object beanList) {
		this.beanList = beanList;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	public int getOffset()
	{
		if (currentPage == 0)
			return 0;
		return (currentPage - 1) * pageSize;
	}
	
	/**
	 * 
	 * @description 总页数
	 * @return
	 * @author qianye.zheng
	 */
	public Integer getTotalPage()
	{
		if (getDataSize() == 0)
		{
			totalPage = 0;
		} else if (getDataSize() <= getPageSize())
		{ // 没有超过1页
			totalPage = 1;
		} else
		{
			totalPage = getDataSize() / getPageSize();
			if (getDataSize() % getPageSize() != 0)
			{
				// 还有下一页，加1
				totalPage += 1;
			}
		}
		
		return totalPage;
	}
}
