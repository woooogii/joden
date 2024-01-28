<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function sendCart() {
	var f = document.myForm;
	f.action = "<%=cp%>/cabin/order/myCart_ok.gos?pageNum=${pageNum}&cartnum=${productList.productNum }"
	f.submit();
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세페이지</title>

<link rel="stylesheet" type="text/css" href="<%=cp%>/joden/product/css1/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=cp%>/joden/product/css1/productDetail.css"/>

</head>
<body>


<form action="" method="post" name="myForm">
<div  id="part_first">

	<div style="display: flex;">
	
			<div class="part_first_image">
				<img alt="" src="${imagePath }/${productList.imgSaveFileName }" class="img"/>
			</div>
			
			<div class="part_first_detail">
				<div class="font_main">
					${productList.category }
					<br/>
					${productList.productName }
					<br/>
					${productList.productDetailContent }
				<br/><br/>
					${productList.price }
				</div>
				
				<div>
				<input type="button" name="buyNow" value="바로구매하기">
				<br/>
				<input type="button" name="cartAmount" value="장바구니추가" onclick="sendCart();">
				<br/>
				<input type="button" name="cart" value="장바구니리스트" onclick="location='<%=cp%>/cabin/order/myCart.gos';">
				
<%-- 				<input type="hidden" name="productNum" value="${productList.productNum }"> --%>
				</div>
			</div>
	</div>

</div>

</form>

</body>
</html>