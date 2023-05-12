package com.myshop.dto;

public class Category {
	private String cate;
	private String categroup;
	private String catename;
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getCategroup() {
		return categroup;
	}
	public void setCategroup(String categroup) {
		this.categroup = categroup;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	@Override
	public String toString() {
		return "Category [cate=" + cate + ", categroup=" + categroup
				+ ", catename=" + catename + "]";
	}
}
