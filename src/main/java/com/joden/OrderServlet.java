package com.joden;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBConn;
import com.util.MyUtil;

public class OrderServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String cp = req.getContextPath();
		
		Connection conn = DBConn.getConnection();
		CartDAO cartDao = new CartDAO(conn);
		OrderDAO odao = new OrderDAO(conn);
		//PageDAO pdao = new PageDAO(conn);
		
		MyUtil myUtil = new MyUtil();

		String uri = req.getRequestURI();
		String url;
		
		String root = getServletContext().getRealPath("/");
		String path = root+"productImgs"+File.separator+"saveImage";
		
		File f = new File(path);
		if(!f.exists()) {
			
			f.mkdirs();
		}
		HttpSession session = req.getSession();
		UserInfo UserInfo = (UserInfo) session.getAttribute("UserInfo");
		String userId = UserInfo.getUserId();
		//장바구니
		if(uri.indexOf("order/myCart.gos")!=-1) {
			int currentPage = 1;
			int dataCount = cartDao.getCartDataCount(userId);
			
			int numPerPage = 5;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage) {
				currentPage = totalPage;
			}
 
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<CartDTO> cartList = cartDao.getCartList(userId, start, end);
			
			String articleUrl = cp + "/cabin/shop/productDetail.gos";
			
			req.setAttribute("cartList", cartList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			
			String imagePath = cp + "/productImgs/saveImage";		
			String deletePath = cp + "/cabin/order/myCartDelete.gos";
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("deletePath", deletePath);
			
			url = "/joden/myPage/myCart.jsp";
				
			forward(req, resp, url);	
			
		}else if(uri.indexOf("order/myCart_ok.gos")!=-1) {
			int productNum = Integer.parseInt(req.getParameter("cartnum"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			System.out.println("productNum" + productNum);
			CartDTO dto = new CartDTO();
			
			dto.setUserId(userId);
			dto.setProductNum(productNum);
			System.out.println("insertcart start");
			
			cartDao.insertCart(dto);

			System.out.println("insertcart end");
			
			url = cp + "/cabin/shop/productDetail.gos?pageNum="+ pageNum +"&num="+productNum;
			resp.sendRedirect(url);
		
		}else if(uri.indexOf("order/myCartDelete.gos")!=-1) {
			
			String productName = req.getParameter("productName");
			
			cartDao.deleteCart(productName);
			
			url = cp + "/cabin/order/myCart.gos";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("order/myOrder.gos")!=-1) {
			
			
			UserInfo info = (UserInfo)session.getAttribute("GosInfo");
			/*
			if(info==null) {
				
				url = "/myPage/login.jsp";
				forward(req, resp, url);
				return;
			}
			*/
			
			String productName = req.getParameter("productName");
			int currentPage = 1;
			
			if(productName!=null) {
				
				currentPage = Integer.parseInt(productName);
			}
			
			int dataCount = odao.getOrderDataCount();
			
			int numPerPage = 10;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage) {
				
				currentPage = totalPage;
			}
 
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<OrderDTO> lists = odao.getOrderLists(start,end);
			
			String imagePath = cp + "/productImgs/saveImage";			
			String deletePath = cp + "/cabin/order/myOrderDelete_ok.gos";			
			String articleUrl = cp + "/cabin/shop/productDetail.gos";
			
			req.setAttribute("lists", lists);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("deletePath", deletePath);
			
			url = "/joden/myPage/myOrder.jsp";
				
			forward(req, resp, url);	
			
		}else if(uri.indexOf("myOrderDelete_ok.do")!=-1) { /////////////////
			
			int orderNum = Integer.parseInt(req.getParameter("orderNum"));
			String pageNum = req.getParameter("pageNum");
			
			odao.deleteDataOrderList(orderNum);
			
			String param = "pageNum=" + pageNum;
			
			//url = cp + "/cabin/myOrder.gos?" + param;
			url = cp + "/cabin/myOrder.gos";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("order/myPage.gos")!=-1) {
			
			
			UserInfo info = (UserInfo)session.getAttribute("GosInfo");
			/*
			if(info==null) {
				
				url = "/myPage/login.jsp";
				forward(req, resp, url);
				return;
			}*/
			
			url = "/myPage/myPage.jsp";
				
			forward(req, resp, url);	
			
		}
	}

}
