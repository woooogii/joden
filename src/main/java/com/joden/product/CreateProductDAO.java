package com.joden.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

public class CreateProductDAO {
	private Connection conn;

	public CreateProductDAO(Connection conn) {
		this.conn = conn;
	}

	//상품등록
	public int insertProduct(CreateProductDTO dto) {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "insert into joden.PRODUCTINFO values (?,?,?,?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(ProductSqlMapper.INSERT_PRODUCT_SQL);

			pstmt.setInt(1, dto.getProductNum());
			pstmt.setString(2, dto.getProductName());
			pstmt.setString(3, dto.getCategory());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getAmount());
			pstmt.setString(6, dto.getImgSaveFileName());
			pstmt.setString(7, dto.getImgOriginalFileName());
			pstmt.setString(8, dto.getProductDetailContent());

			rs = pstmt.executeQuery();

			if(rs.next()) {

				dto.setProductNum(rs.getInt("productNum"));
				dto.setProductName(rs.getString("productName"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
				dto.setImgOriginalFileName(rs.getString("imgOriginalFileName"));
				dto.setProductDetailContent(rs.getString("productDetailContent"));

			}

			result = pstmt.executeUpdate();

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		return result;
	}

	//전상품 select
	public List<CreateProductDTO> allProductList(int start,int end, String searchValue){
		List<CreateProductDTO> allProductLists = new ArrayList<CreateProductDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		String sql = "select p.* from (select row_number() over(order by productNum desc)as rnum,data.* from (select * from joden.PRODUCTINFO where productName like ? or category like ?) as data) as p where rnum>=? and rnum <=?;";
		
		try {
			pstmt = conn.prepareStatement(ProductSqlMapper.ALL_PRODUCT_LIST_SQL);
			pstmt.setString(1, "%" + searchValue + "%");
			pstmt.setString(2, "%" + searchValue + "%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				CreateProductDTO dto = new CreateProductDTO();

				dto.setProductNum(rs.getInt("productNum"));
				dto.setProductName(rs.getString("productName"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
				dto.setImgOriginalFileName(rs.getString("imgOriginalFileName"));
				dto.setProductDetailContent(rs.getString("productDetailContent"));

				allProductLists.add(dto);	
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return allProductLists;
	}


	//카테고리별 상품 select
	public List<CreateProductDTO> cateProductList(String category,int start,int end){
		List<CreateProductDTO> categoryLists = new ArrayList<CreateProductDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select p.* from (select row_number() over(order by productNum desc)as rnum,data.* from (select * from joden.PRODUCTINFO where category =?) as data) as p where rnum>=? and rnum <=?";
		try {

			pstmt = conn.prepareStatement(ProductSqlMapper.CATE_PRODUCT_LIST_SQL);

			pstmt.setString(1, category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				CreateProductDTO dto = new CreateProductDTO();

				dto.setProductNum(rs.getInt("productNum"));
				dto.setProductName(rs.getString("productName"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
				dto.setImgOriginalFileName(rs.getString("imgOriginalFileName"));
				dto.setProductDetailContent(rs.getString("productDetailContent"));

				categoryLists.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return categoryLists;
	}
	
	//1개 상품 select
	public CreateProductDTO getReadProduct(int productNum) {
		CreateProductDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select * from joden.PRODUCTINFO where productNum=?";

		try {
			pstmt = conn.prepareStatement(ProductSqlMapper.GET_READ_PRODUCT_SQL);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dto = new CreateProductDTO();

				dto.setProductNum(rs.getInt("productNum"));
				dto.setProductName(rs.getString("productName"));
				dto.setCategory(rs.getString("category"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				dto.setImgSaveFileName(rs.getString("imgSaveFileName"));
				dto.setImgOriginalFileName(rs.getString("imgOriginalFileName"));
				dto.setProductDetailContent(rs.getString("productDetailContent"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	//상품코드
	public int getAllProductMaxNum() {

		int maxNum = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select ifnull(max(productNum),0) from joden.PRODUCTINFO";

		try {

			pstmt = conn.prepareStatement(ProductSqlMapper.GET_ALL_PRODUCT_MAXNUM_SQL);

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
		
	//상품 수량(페이징처리)
	public int getAllDataCount(String searchValue) {
		int dataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select ifnull(count(*),0) from joden.PRODUCTINFO where productName like ? or category like ?";

		try {

			pstmt = conn.prepareStatement(ProductSqlMapper.GET_ALL_DATA_COUNT_SQL);
			pstmt.setString(1, "%" + searchValue + "%");
			pstmt.setString(2, "%" + searchValue + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dataCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dataCount;

	}

	public int getCategoryDataCount(String category) {
		int dataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql = "select ifnull(count(*),0) from (select * from joden.PRODUCTINFO where category = ?) as p";

		try {

			pstmt = conn.prepareStatement(ProductSqlMapper.GET_CATEGORY_DATA_COUNT_SQL);
			pstmt.setString(1, category);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				dataCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dataCount;

	}
	
	public int deleteData(int productNum) {
		int result = 0;
		
		PreparedStatement pstmt = null;
//		String sql = "delete from joden.PRODUCTINFO where productNum = ?";
		
		try {
			
			pstmt = conn.prepareStatement(ProductSqlMapper.DELETE_DATA_SQL);
			pstmt.setInt(1, productNum);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

}
