<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

<link rel="stylesheet" type="text/css"
	href="<%=cp%>/joden/login/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/joden/login/css/created.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<script type="text/javascript" src="<%=cp%>/joden/login/js/util.js"></script>
<script type="text/javascript">


	function checkAll() {
		  var checkboxes = document.myForm["str2"];
		  var clength = checkboxes.length;
	
		  for (var i = 0; i < clength; i++) {
		    checkboxes[i].checked = !checkboxes[i].checked;
		  }
		}

	function sendIt() {
		var f = document.myForm;
		
		str = f.userId.value;
		str = str.trim();
		if(!str) {
			alert("아이디를 입력하세요.");
			f.userId.focus();
			return;	
		}
	
		str = f.userId.value;
	
		str = f.userPwd.value;
		str = str.trim();
		if(!str) {
			alert("비밀번호를 입력하세요.");
			f.userPwd.focus();
			return;
		}
		
		str = f.userPwd.value;
		
		str = f.userPwd2.value;
		str = str.trim();
		if(!str) {
			alert("확인용 비밀번호를 입력하세요.");
			f.userPwd2.focus();
			return;
		}
		
		if(f.userPwd.value != f.userPwd2.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.userPwd.value="";
			f.userPwd2.value="";
			f.userPwd.focus();
			return;
		}
		
		
		str = f.userPwd2.value;
		
		str = f.userName.value;
	
		str = f.userName.value;
		str = str.trim();
		if(!str) {
			alert("이름을 입력하세요.");
			f.userName.focus();
			return;
		}
		
		str = f.userEmail.value;
		
		str = f.userEmail.value
		str = str.trim();
		if(!str) {
			alert("이메일을 입력하세요.");
			f.userEmail.focus();
			return;
		}
		
		str = f.userEmail.value;
		
		str = f.userTel.value;
		str = str.trim();
		if(!str) {
			alert("전화번호를 입력하세요.");
			f.userTel.focus();
			return;
		}
		
		str = f.userTel.value;
		
		str = f.userGender.value;
		str = str.trim();
		if(!str) {
			alert("성별을 선택하세요.");
			f.userGender.focus();
			return;
		}
		
		str = f.userGender.value;
		
		str = f.userAddr.value;
		str = str.trim();
		if(!str) {
			alert("주소를 입력하세요.");
			f.userAddr.focus();
			return;
		}
		
		

		str = f.userAddr.value;
		
		str = f.userBirth.value;
		str = str.trim();
		if(!str) {
			alert("생일을 입력하세요.");
			f.userBirth.focus();
			return;
		}
		
		str = f.userBirth.value;
		
		f.action = "<%=cp%>
	/cabin/membership/created_ok.gos"
		f.submit();

	}
</script>

<script language="javascript">
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/joden//joden/login/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadFullAddr) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.myForm.userAddr.value = roadFullAddr;

	}
</script>

<style type="text/css">
body {
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background: url('<%=cp%>/login/img.jpg') no-repeat;
	background-size: cover;
	background-position: center;
}
</style>


</head>
<body>

	<div id="bbs" style="text-align: center; align: center"
		style="margin: 0 auto;">

		<form action="" method="post" name="myForm">
			<div id="bbs_title" style="width: 300px; margin: 0 auto;">
				<h1>Regisitration</h1>
			</div>

			<div class="input-box">
				<input name="userId" type="text" placeholder="UserId" maxlength="50"
					required>
			</div>

			<div class="input-box">
				<input name="userId" type="password" placeholder="UserPassword"
					maxlength="50" required>

			</div>

			<div class="input-box">
				<input name="userId" type="password" placeholder="Password Check"
					maxlength="50" required>
			</div>

			<div class="input-box">
				<input name="userId" type="text" placeholder="UserName"
					maxlength="50" required>
			</div>


			<div class="input-box">
				<input name="userId" type="text" placeholder="UserEmail"
					maxlength="50" required>
			</div>


			<div class="input-box">
				<input name="userId" type="text" placeholder="UserTel"
					maxlength="50" required>
			</div>


			<div class="input-box">
				<input name="userId" type="text" placeholder="UserGender"
					maxlength="50" required> <input type="radio"
					name="userGender" size="35" maxlength="50" class="boxTF" value="남자" />남자 
				<input type="radio" name="userGender" size="35" maxlength="50" 
					class="boxTF" value="여자" /> 여자
			</div>


			<div class="input-box">
				<input name="userId" type="text" placeholder="UserAddress"
					maxlength="50" required>
	            <input type="button" onClick="goPopup();" value="주소검색"/>
				
			</div>



			<div class="input-box">
				<input name="userId" type="text" placeholder="UserBirth"
					maxlength="50" required>
			</div>


			<div class="input-box">
				<input name="userId" type="text" placeholder="UserReg"
					maxlength="50" required>
			</div>


			<br />




			<div style="border: 1px solid #fff; width: 300px; margin: 0 auto; padding: 10px;">
				<div colspan="3" name="str2" value="전체동의">
					<h1></h1>

					<input type="checkbox" onclick="checkAll();"> </a>[필수] 인증 약관
					전체동의
				</div>
				<div style="display: flex; justify-content: space-between;">
					<div>
						<input type="checkbox" name="str2"> <a
							href="https://nid.naver.com/user2/common/terms/terms2?t=viewPersonalInfoTerms&v=1">
							개인정보이용 </a>
					</div>
					<div>
						<input type="checkbox" name="str2"> <a
							href="https://nid.naver.com/user2/common/terms/terms2?t=viewUniqInfoTerms&v=1">
							고유식별정보처리 </a>
					</div>
				</div>
				<div style="display: flex; justify-content: space-between;">
					<div>
						<input type="checkbox" name="str2"> <a
							href="https://nid.naver.com/user2/common/terms/terms2?t=viewCellPhoneCarriersTerms&v=1">
							통신사 이용약관 </a>
					</div>
					<div>
						<input type="checkbox" name="str2"> <a
							href="https://nid.naver.com/user2/common/terms/terms2?t=viewServiceTerms&v=1">
							이용사 이용약관 </a>
					</div>
				</div>
			</div>

			<br />
			<div id="button" style="width: 300px; margin: 0 auto;">
				<input type="button" value="가입하기" class="btn2" onclick="sendIt();" />
				<input type="button" value=" 작성취소 " class="btn2"
					onclick="location='<%=cp%>/cabin/membership/login.gos';"/>
			</div>
		
		</form>

	</div>





</body>
</html>