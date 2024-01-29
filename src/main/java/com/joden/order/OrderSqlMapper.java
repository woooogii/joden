package com.joden.order;

class OrderSqlMapper {

	static final String GET_ORDER_LISTS_SQL 
	= "select p.* "
			+ "from ("
				+ "select row_number() over(order by orderNum desc) rnum,data.* "
				+ "from ("
					+ "select productName,userId,payWay,orderAmount,totalPrice,orderNum,DATE_FORMAT(orderDate,'YYYY-MM-DD') orderDate,imgSaveFileName "
					+ "from joden.ORDERLIST)as data) as p "
					+ "where rnum>=? and rnum<=?";
	
	static final String GET_READ_ORDER_DATA_SQL = "select * from joden.ORDERLIST where orderNum=?";
	
	static final String DELETE_DATA_ORDER_LIST_SQL = "delete from joden.ORDERLIST where orderNum=?";
}
