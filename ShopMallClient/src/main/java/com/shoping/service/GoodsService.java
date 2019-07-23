package com.shoping.service;

import java.util.List;

import com.shoping.entity.Goods;

/**
 * 有关库中商品的服务层
 * @author zhuxu
 *
 */
public interface GoodsService {
	/**
	 * 商品库中查询商品
	 * @return
	 */
	public List<Goods> findGoods(Goods goods);

	/**
	 * 根据id查询商品
	 * （应该与findGoods合一个sql写）
	 * @param id
	 * @return
	 */
	public  Goods  findGoodsById(String id);

	/**
	 * 修改库存量
	 * @param gooGoodsds 
	 * @param goodsSum
	 */
	public void updateStock(Goods gooGoodsds, int goodsSum);
}
