package com.SCMXPert.sbmongodb.document;

import java.util.List;

public class Deletegood {
	
	private String Customer_Id;
	private List<Goods> Goods_Id;
	public String getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}
	public List<Goods> getGoods_Id() {
		return Goods_Id;
	}
	public void setGoods_Id(List<Goods> goods_Id) {
		Goods_Id = goods_Id;
	}

}
