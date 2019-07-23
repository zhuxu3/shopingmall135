package com.shoping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shoping.entity.Goods;

@Mapper
public interface GoodsDao {
	public List<Goods> findGoods();

	public Goods findGoodsById(@Param(value="id")String id);

	public void updateStock(@Param(value="surplus")String surplus, @Param(value="goodsId")String goodsId);

}
