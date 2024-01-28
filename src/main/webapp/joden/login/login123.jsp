<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="<%=cp %>/joden/login/css/style.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

<script type="text/javascript">


function login(){
	
	var f = document.myForm;
	
	if(!f.userId.value){
		alert("아이디를 입력하세요.");
		f.userId.focus();
		return;
	}
	
	if(!f.userPwd.value){
		alert("패스워드를 입력하세요.");
		f.userPwd.focus();
		return;
	}
	
	f.action = "<%=cp %>/cabin/membership/login_ok.gos";
	f.submit();
	
}

</script>

<style type="text/css">
body{
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background: url('<%=cp%>/joden/login/magic22.png') no-repeat;
	background-size: cover;
	background-position: center;
}
</style>

</head>

<body>
	<div class="wrapper">
		<form action="<%=cp%>/cabin/membership/login_ok.gos" method="post" name="myForm">
			<h1>Login</h1>
			<div class="input-box">
				<input name="userId" type="text" placeholder="Username" maxlength = "50" required>
				<i class='bx bxs-user'></i>
			</div>
			
			
			<div class="input-box">
				<input type="password" name="userPwd" placeholder="Password" maxlength = "50" required>	
				<i class='bx bxs-lock alt'></i>
			</div>
			
			<font size="2">${message1 }<br/><br/></font>
			<button onclick="login();" class="btn">로그인</button>
			
			<div class="register-link">
				<p>계정이 없으신가요? <a href="<%=cp %>/cabin/membership/created.gos">회원가입</a></p>
			</div>
		
		
		
		
		</form>
	</div>


</body>
</html>