package com.tcs.ins.testcase.service.model;

public class TestCaseModel {

	private Long id;
	
	private String name;
	private String desc;
	private String category;
	private String subCategory;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "TestCaseModel [id=" + id + ", name=" + name + ", desc=" + desc + ", category=" + category
				+ ", subCategory=" + subCategory + "]";
	}
}