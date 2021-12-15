package com.tcs.ins.testcase.service.model;

import java.util.Map;

import org.springframework.util.StringUtils;

public class TestCaseSearchRequest {

	private static final String REQUEST_PARAM_ID = "id";
	private static final String REQUEST_PARAM_NAME = "name";
	private static final String REQUEST_PARAM_CAT = "category";
	private static final String REQUEST_PARAM_SUBCAT = "subCategory";
	private static final String REQUEST_PARAM_DESC = "desc";

	private final Long id;
	
	private final String name;
	private final String desc;
	private final String category;
	private final String subCategory;

	public TestCaseSearchRequest(Map<String, String> requestParam) {
		String idStr = requestParam.get(REQUEST_PARAM_ID);
		if (StringUtils.hasText(idStr)) {
			this.id = Long.valueOf(idStr);
		} else {
			this.id = null;
		}
		this.name = requestParam.get(REQUEST_PARAM_NAME);
		this.category = requestParam.get(REQUEST_PARAM_CAT);
		this.subCategory = requestParam.get(REQUEST_PARAM_SUBCAT);
		this.desc = requestParam.get(REQUEST_PARAM_DESC);
	}

	public boolean idFilteringRequired() {
		return id != null;
	}

	public boolean nameFilteringRequired() {
		return StringUtils.hasText(name);
	}

	public boolean categoryFilteringRequired() {
		return StringUtils.hasText(category);
	}

	public boolean subCategoryFilteringRequired() {
		return StringUtils.hasText(subCategory);
	}

	public boolean descFilteringRequired() {
		return StringUtils.hasText(desc);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}
}
