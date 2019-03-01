package com.zrytech.framework.app.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.zrytech.framework.base.entity.PageData;

@Component
public class PageDataUtils<T> {

	
	/**
	 * 将 JPA返回的分页数据绑定到自定义的 {@link PageData}中
	 * 
	 * @param page	JPA返回的分页数据
	 * @return	自定义的 {@link PageData}
	 */
	public PageData<T> bindingData(Page<T> page) {
		PageData<T> pageData = new PageData<T>();
		pageData.setList(page.getContent());
		pageData.setPageNo(page.getNumber() + 1);
		pageData.setPageSize(page.getSize());
		pageData.setTotal(page.getTotalElements());
		return pageData;
	}
	
}
