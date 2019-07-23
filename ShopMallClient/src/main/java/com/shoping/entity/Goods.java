package com.shoping.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体类
 * @author zhuxu
 *
 */
public class Goods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3958667236898011782L;
	private String goodsId;          //商品编号
	private String goodsName;        //商品名称
	private String stock;            //库存量
	private String goodsType;        //商品类型
	private BigDecimal price;               //价格
	private String goodsNum;         //购买物品数量
	
	
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	
	

}
