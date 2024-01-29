package com.joden.user;

class UserSqlMapper {

	static final String INSERT_DATA_SQL = "insert into USERINFO values (?,?,?,?,?,?,?,?,now())";
	
	static final String GET_READ_DATA_SQL = "select * from joden.USERINFO where userId=?";
	
	static final String GET_READ_DATA1_SQL = "select userId from joden.USERINFO where userTel=? and userEmail=?";
	
	static final String GET_READ_DATA2_SQL = "select userPwd from joden.USERINFO where userId=? and userEmail=?";
	
	static final String UPDATE_DATA_SQL = "update joden.USERINFO set userPwd=?,userName=?,userEmail=?,userTel=?,userGender=?,userAddr=?,userBirth=? where userId=?";
	
	static final String FORGOT_ID_SQL = "select userId from joden.USERINFO where userTel=? and userEmail=?";
	
}
