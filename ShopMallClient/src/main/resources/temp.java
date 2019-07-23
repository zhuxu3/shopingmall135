
public class temp {
	<form action="">
	<table>
	
	</table>
		<header><span>商品编号</span><span>商品名称</span><span>库存</span><span>商品类型</span><span>购买数量</span> </header>
		<div th:each="goods,userStatus:${goods}">
			<input type="text" th:value="*{goods.goodsId}" /> <input type="text"
				th:value="*{goods.goodsName}" /> <input type="text"
				th:value="*{goods.stock}" /> <input type="text"
				th:value="*{goods.goodsType}" /> <input type="text" />
		</div>
		<table border="1">
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>库存</th>
			<th>类型</th>
			<th>价格</th>
			<th>选择物品</th>
			<td th:text="${customer}">
		</tr>
		<tr th:each="goods,var:${goods}">
			<td th:text="${goods.goodsId}" />
			<td th:text="${goods.goodsName}" />
			<td th:text="${goods.stock}" />
			<td th:text="${goods.goodsType}" />
			<td th:text="${goods.price}" />
			<td>
				<button
					th:onclick="|test('${goods.goodsId}','${goods.goodsName}','${customer}' )|">添加</button>
			</td>
		</tr>
	</table>
	</form>

}
