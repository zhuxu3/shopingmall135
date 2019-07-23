package com.shoping.entity;

import java.io.Serializable;
import java.util.List;

public class Shopping implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8295198970463294043L;

	private List<Goods> goods;    //商品信息
	
	private String buyNum;      //购买数量

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	@Override
	public String toString() {
		return "Shopping [goods=" + goods + ", buyNum=" + buyNum + "]";
	}

	
	

}
