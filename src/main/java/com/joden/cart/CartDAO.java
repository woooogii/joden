package com.joden.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CartDAO {

	private Connection conn;

	public CartDAO(Connection conn) {
		this.conn = conn;
	}

	//	public  List<CartDTO> getCartLists(int start,int end){
	//		List<CartDTO> lists = new ArrayList<CartDTO>();
	//		PreparedStatement pstmt = null;
	//		ResultSet rs = null;
	//		String sql;
	//		
	//		try {
	//
	//			sql = "select * from (select rownum rnum, data.* from (";
	//			sql+= "select userId,productName,cartAmount,price,imgSaveFileName ";
	//			sql+= "from CART) Data) where rnum>=? and rnum<=?";
	//			pstmt = conn.prepareStatement(sql);
	//
	//			pstmt.setInt(1, start);
	//			pstmt.setInt(2, end);
	//			
	//			rs = pstmt.executeQuery();
	//			while(rs.next()) {
	//				
	//				CartDTO dto = new CartDTO();
	//
	//				dto.setUserId(rs.getString("userId"));
	//				dto.setProductName(rs.getString("productName"));
	//				dto.setCartAmount(rs.getInt("cartAmount"));
	//				dto.setPrice(rs.getInt("price"));
	//				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
	//				
	//				lists.add(dto);
	//			}
	//			
	//			rs.close();
	//			pstmt.close();
	//		} catch (Exception e) {
	//			
	//			System.out.println(e.toString());
	//		}
	//		
	//		return lists;
	//	}

	public CartDTO getReadCartData(String productName) {

		CartDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
//			String sql = "SELECT b.productName,b.userId,b.cartAmount,";
//			sql+= "b.Price, b.imgSaveFileName FROM CART ";
//			sql+= "b INNER JOIN PRODUCTINFO p ON b.productName ";
//			sql+= "= p.productName WHERE b.productName=?";

			pstmt = conn.prepareStatement(CartSqlMapper.GET_READ_CART_DATA_SQL);

			pstmt.setString(1, productName);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				dto = new CartDTO();

				dto.setUserId(rs.getString("userId"));
				dto.setProductName(rs.getString("productName"));
				dto.setCartAmount(rs.getInt("cartAmount"));
				dto.setPrice(rs.getInt("Price"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return dto;
	}

	public int deleteCart(String productName) {

		int result = 0;

		PreparedStatement pstmt = null;
	

		try {

//			String sql = "delete CART where productName=?";

			pstmt = conn.prepareStatement(CartSqlMapper.DELETE_CART_SQL);

			pstmt.setString(1, productName);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return result;
	}

	public int updateCart(CartDTO dto) {

		int result = 0;

		PreparedStatement pstmt = null;
	

		try {

//			String sql = "update CART set cartAmount=? where productName=?";

			pstmt = conn.prepareStatement(CartSqlMapper.UPDATE_CART_SQL);

			pstmt.setInt(1, dto.getCartAmount());
			pstmt.setString(2, dto.getProductName());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return result;
	}

	public boolean isCart(String userId) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select * from joden.cart where userId=?";
		try {
			pstmt = conn.prepareStatement(CartSqlMapper.IS_CART_SQL);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				return true;	
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public int insertCart(CartDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
//		String sql = "insert into joden.cart (userId,productNum) values(?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(CartSqlMapper.INSERT_CART_SQL);
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getProductNum());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	public List<CartDTO> getCartList(String userId,int start,int end){
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//		String sql = "select p.* from (select @rownum := @rownum + 1 rnum, data.* from (select c.cartId, p.productName, p.price, p.imgSaveFileName from joden.cart c INNER JOIN joden.PRODUCTINFO p on c.productNum = p.productNum WHERE userId = ?) as data) as p where rnum>=? and rnum<=?";
		try {
			

			pstmt = conn.prepareStatement(CartSqlMapper.GET_CART_LIST_SQL);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			while(rs.next()) {

				CartDTO dto = new CartDTO();

				dto.setCartId(rs.getInt("cartId"));
				dto.setProductName(rs.getString("productName"));
				dto.setPrice(rs.getInt("price"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));

				cartList.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return cartList;
	}

	public int getCartDataCount(String userId) {

		int dataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			String sql = "select ifnull(count(*),0) from joden.CART where userId = ?";
			pstmt = conn.prepareStatement(CartSqlMapper.GET_CART_DATA_COUNT_SQL);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dataCount = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return dataCount;
	}
	
	public int getCartId() {

		int cartId = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select ifnull(max(cartId),0) from joden.cart";

		try {

			pstmt = conn.prepareStatement(CartSqlMapper.GET_CART_ID_SQL);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				cartId = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return cartId;

	}
	
	public int getCartAmount() {

		int maxNum = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select ifnull(max(cartAmount),0) from joden.cart";

		try {

			pstmt = conn.prepareStatement(CartSqlMapper.GET_CART_AMOUNT_SQL);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				maxNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return maxNum;

	}

}
