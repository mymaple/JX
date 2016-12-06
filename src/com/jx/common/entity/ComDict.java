package com.jx.common.entity;

public class ComDict {
	
	private int dictId;
	private String name;
	private String encode;
	private int orderBy;
	private int parentId;
	private int level;
	private String allEncode;
	
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getAllEncode() {
		return allEncode;
	}
	public void setAllEncode(String allEncode) {
		this.allEncode = allEncode;
	}

	
	
	
	
	
}
