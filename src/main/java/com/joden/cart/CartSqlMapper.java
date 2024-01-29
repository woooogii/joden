package com.joden.cart;


//default(생략) 클래스 & 변수로 해당 패키지에서만 접근 가능하도록 함
class CartSqlMapper {
	
	static final String GET_READ_CART_DATA_SQL 
	="SELECT b.productName,b.userId,b.cartAmount b.Price, b.imgSaveFileName "
			+ "FROM CART b "
				+ "INNER JOIN PRODUCTINFO p "
				+ "ON b.productName = p.productName "
			+ "WHERE b.productName=? ";
	
	
	static final String DELETE_CART_SQL = "delete CART where productName=?";
	
	static final String UPDATE_CART_SQL = "update CART set cartAmount=? where productName=?";
	
	static final String IS_CART_SQL = "select * from joden.cart where userId=?";
	
	static final String INSERT_CART_SQL = "insert into joden.cart (userId,productNum) values(?,?)";
	
	static final String GET_CART_LIST_SQL 
	= "select p.* "
			+ "from ("
				+ "select @rownum := @rownum + 1 rnum, data.* "
				+ "from ("
					+ "select c.cartId, p.productName, p.price, p.imgSaveFileName "
					+ "from joden.cart c "
						+ "INNER JOIN joden.PRODUCTINFO p "
						+ "on c.productNum = p.productNum "
						+ "WHERE userId = ?"
				+ ")as data"
			+ ") as p "
			+ "where rnum>=? and rnum<=?";
	
	static final String GET_CART_DATA_COUNT_SQL = "select ifnull(count(*),0) from joden.CART where userId = ?";
	
	static final String GET_CART_ID_SQL = "select ifnull(max(cartId),0) from joden.cart";
	
	static final String GET_CART_AMOUNT_SQL = "select ifnull(max(cartAmount),0) from joden.cart";
}
