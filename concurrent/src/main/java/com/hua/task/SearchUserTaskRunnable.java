/**
  * @filename SearchUserTaskRunnable.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.task;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hua.bean.Pager;
import com.hua.util.JacksonUtil;

/**
 * @type SearchUserTaskRunnable
 * @description 
 * @author qianye.zheng
 */
public class SearchUserTaskRunnable implements Runnable
{

	/* 页_大小 */
	private Integer pageSize;
	
	/* 当前页，从1开始 */
	private Integer currentPage = 1;
	
	/**
	 * @description 构造方法
	 * @param pageSize
	 * @param currentPage
	 * @author qianye.zheng
	 */
	public SearchUserTaskRunnable(Integer pageSize)
	{
		super();
		this.pageSize = pageSize;
	}

	/**
	 * @description 
	 * @author qianye.zheng
	 */
	@Override
	public void run()
	{
		System.out.println("SearchUserTaskRunnable.run()");
		// 构造请求对象
		final StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(messageConverter);
		final RestTemplate request = new RestTemplate(messageConverters);
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add("Accept", "application/json; charset=UTF-8");
		headers.add("APP-ID", "100001");
		headers.add("X-AUTH-HEADER", "AFKJLKWEOIK02LKJAM23OKASK");
		String url = "http://10.237.151.181:8085/hotel-manage-platform-api/rest/user/search/v1";
		Pager pager = new Pager();
		pager.setPageSize(pageSize);
		pager.setCurrentPage(currentPage);
		String body = JacksonUtil.writeAsString(pager);
		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> entity = request.exchange(url, HttpMethod.POST, requestEntity, String.class);
		String result = entity.getBody().toString();
		
		/**
		 * 伪造异常，让任务不能成功执行完
		 */
		if (currentPage == 20)
		{
			throw new RuntimeException("伪造异常，让任务不能成功执行完");
		}
		
		System.out.println(Thread.currentThread().getName() + ":\n" + result);
	}

	/**
	* @return the pageSize
	*/
	public final Integer getPageSize()
	{
		return pageSize;
	}

	/**
	* @param pageSize the pageSize to set
	*/
	public final void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	* @return the currentPage
	*/
	public final Integer getCurrentPage()
	{
		return currentPage;
	}

	/**
	* @param currentPage the currentPage to set
	*/
	public final void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}
	
}
