<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

function searchData() {
	var f= document.searchForm;
	
	f.action="<%=cp%>/cabin/shop/productList.gos";
	f.submit();
	
}

</script>
</head>
<body>

<c:choose>
	<c:when test="${empty sessionScope.UserInfo.userId }">
		 <div id="header-first">
			<div style="display: flex;">
				<span class="header-join">
				<a href="<%=cp%>/cabin/membership/created.gos">회원가입</a>
				</span>
				<span id="header-first-line">ㅣ</span>
				<span class="header-login">
				<a href="<%=cp%>/cabin/membership/login.gos">로그인</a>
				</span>
			</div>
		</div>
	</c:when>
	
	<c:otherwise>
		<div style="display: flex;">
			<span class="header-login">
				<a href="<%=cp%>/cabin/membership/login.gos">로그아웃</a>
			</span>
			<span class="header-login">
				<a href="<%=cp%>/cabin/membership/updated.gos">정보수정</a>
			</span>
		</div>
	</c:otherwise>
</c:choose>
	
<form action="" name="searchForm" method="post">		
	<div id="header-first">
		<div style="display: flex;" class="">
			<div>
			카테고리
				<ul>
 					<li><a href="<%=cp%>/cabin/shop/productList.gos">전체보기</a></li>
 					<li><a href="<%=cp%>/cabin/shop/cateList.gos?category=의류">의류</a></li>
 					<li><a href="<%=cp%>/cabin/shop/cateList.gos?category=무기">무기</a></li>
 					<li><a href="<%=cp%>/cabin/shop/cateList.gos?category=도서">도서</a></li>
 					<li><a href="<%=cp%>/cabin/shop/cateList.gos?category=음료">음료</a></li>
				</ul>
			</div>
			<div>
				<span id="">
					<a href="<%=cp%>/cabin/main.gos"><img alt="" src=""></a>
				</span>
				<span class="">
					<input type="text" name="searchValue" class="textField" placeholder="상품명 검색">
					<input type="button" value="검색" class="btn2" onclick="searchData();">
				</span>
				<span class="">
				<a href="">마이페이지</a>
				</span>
				<span class="">
				<a href="">장바구니</a>
				</span>
			</div>
		</div>
	</div>

</form>


<br/><br/>
</body>
</html>