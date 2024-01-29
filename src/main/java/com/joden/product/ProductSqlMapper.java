package com.joden.product;

class ProductSqlMapper {
	
	static final String INSERT_PRODUCT_SQL = "insert into joden.PRODUCTINFO values (?,?,?,?,?,?,?,?)";
	
	static final String ALL_PRODUCT_LIST_SQL
	= "select p.* "
			+ "from ("
				+ "select row_number() over(order by productNum desc)as rnum,data.* "
				+ "from ("
					+ "select * "
					+ "from joden.PRODUCTINFO "
					+ "where productName like ? "
					+ "or category like ?) as data"
			+ ") as p "
			+ "where rnum>=? and rnum <=?;";
	
	static final String CATE_PRODUCT_LIST_SQL
	= "select p.* "
			+ "from ("
				+ "select row_number() over(order by productNum desc)as rnum,data.* "
				+ "from ("
					+ "select * "
					+ "from joden.PRODUCTINFO "
					+ "where category =?"
				+ ") as data"
			+ ") as p "
			+ "where rnum>=? "
			+ "and rnum <=?";
	
	static final String GET_READ_PRODUCT_SQL = "select * from joden.PRODUCTINFO where productNum=?";
	
	static final String GET_ALL_PRODUCT_MAXNUM_SQL = "select ifnull(max(productNum),0) from joden.PRODUCTINFO";
	
	static final String GET_ALL_DATA_COUNT_SQL = "select ifnull(count(*),0) from joden.PRODUCTINFO where productName like ? or category like ?";

	static final String GET_CATEGORY_DATA_COUNT_SQL = "select ifnull(count(*),0) from (select * from joden.PRODUCTINFO where category = ?) as p";
	
	static final String DELETE_DATA_SQL = "delete from joden.PRODUCTINFO where productNum = ?"; 
}
