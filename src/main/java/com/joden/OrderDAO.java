package com.joden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	
private Connection conn;
	
	public OrderDAO(Connection conn) {
		
		this.conn = conn;
	}
	
public int getOrderDataCount() {
		
		int dataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select ifnull(count(*),0) from joden.ORDERLIST";
			
			pstmt = conn.prepareStatement(sql);
			
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

public  List<OrderDTO> getOrderLists(int start,int end){
	
	List<OrderDTO> lists = new ArrayList<OrderDTO>();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	
	try {
		
		sql = "select p.* from (select row_number() over(order by orderNum desc) rnum,data.* from (select productName,userId,payWay,orderAmount,totalPrice,orderNum,DATE_FORMAT(orderDate,'YYYY-MM-DD') orderDate,imgSaveFileName from joden.ORDERLIST)as data) as p where rnum>=? and rnum<=?";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			OrderDTO dto = new OrderDTO();

			dto.setProductName(rs.getString("productName"));
			dto.setUserId(rs.getString("userId"));
			dto.setPayWay(rs.getString("payWay"));
			dto.setOrderAmount(rs.getInt("orderAmount"));
			dto.setTotalPrice(rs.getInt("totalPrice"));
			dto.setOrderNum(rs.getInt("orderNum"));
			dto.setOrderDate(rs.getString("orderDate"));
			dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
			
			lists.add(dto);
		}
		
		rs.close();
		pstmt.close();
	} catch (Exception e) {
		
		System.out.println(e.toString());
	}
	
	return lists;
}

public OrderDTO getReadOrderData(int orderNum) {
	
	OrderDTO dto = null;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	
	try {
		
		sql = "select * from joden.ORDERLIST where orderNum=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, orderNum);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			dto = new OrderDTO();

			dto.setOrderNum(rs.getInt("orderNum"));
			dto.setProductName(rs.getString("productName"));
			dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
			dto.setUserId(rs.getString("userId"));
			dto.setPayWay(rs.getString("payWay"));
			dto.setOrderAmount(rs.getInt("orderAmount"));
			dto.setTotalPrice(rs.getInt("totalPrice"));
			dto.setOrderDate(rs.getString("orderDate"));
		}
		
		rs.close();
		pstmt.close();
	} catch (Exception e) {
		
		System.out.println(e.toString());
	}
	
	return dto;
}

public int deleteDataOrderList(int orderNum) {
	
	int result = 0;
	
	PreparedStatement pstmt = null;
	String sql;
	
	try {
		
		sql = "delete from joden.ORDERLIST where orderNum=?";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, orderNum);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
	} catch (Exception e) {

		System.out.println(e.toString());
	}
	
	return result;
}

/*
public int updateCart(JodenDTO2 dto) {
	
	int result = 0;
	
	PreparedStatement pstmt = null;
	String sql;
	
	try {
		
		sql = "update CART set cartAmount=? where productName=?";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, dto.getCartAmount());
		pstmt.setString(2, dto.getProductName());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
	} catch (Exception e) {
		
		System.out.println(e.toString());
	}
	
	return result;
}


// num�� max ���ϱ�
public int getMaxNum() {
	
	int maxNum = 0;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	
	try {
		
		sql = "select nvl(max(num),0) from board";
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			maxNum = rs.getInt(1);
		}
		
		rs.close();
		pstmt.close();
	} catch (Exception e) {

		System.out.println(e.toString());
	}
	
	return maxNum;
}

// �Է�
public int insertData(BoardDTO dto) {
	
	int result = 0;
	
	PreparedStatement pstmt = null;
	String sql;
	
	try {
		
		sql = "insert into board (num,name,pwd,email,subject,content,";
		sql+= "ipAddr,hitCount,created) values (?,?,?,?,?,?,?,0,sysdate)";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getNum());
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getPwd());
		pstmt.setString(4, dto.getEmail());
		pstmt.setString(5, dto.getSubject());
		pstmt.setString(6, dto.getContent());
		pstmt.setString(7, dto.getIpAddr()); // created �� hitCount �� �ڵ����� ��
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
	} catch (Exception e) {
		
		System.out.println(e.toString());
	}
	
	return result;
}
*/

}
