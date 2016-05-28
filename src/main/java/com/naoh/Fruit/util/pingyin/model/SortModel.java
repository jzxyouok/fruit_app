package com.naoh.Fruit.util.pingyin.model;

import com.naoh.Fruit.Data.SellerBean;

public class SortModel
{
	private int id;
	private String name;
	private String info;
	
	private String sortLetters;
	SellerBean sellerBean;

	public SellerBean getSellerBean() {
		return sellerBean;
	}

	public void setSellerBean(SellerBean sellerBean) {
		this.sellerBean = sellerBean;
	}

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSortLetters()
	{
		return sortLetters;
	}
	public void setSortLetters(String sortLetters)
	{
		this.sortLetters = sortLetters;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	
}
