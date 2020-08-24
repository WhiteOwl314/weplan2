<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${contextPath }/resources/css/reset.css" rel="stylesheet" type="text/css">
<link href="${contextPath }/resources/css/baselayout/member/mypage.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		if(${msg ne null}){
			alert('${msg}');
		};
		
		if($("#pwForm").submit(function(){
			if($("#pwd").val() !== $("#pw2").val()){
				alert("비밀번호가 다릅니다.");
				$("#pwd").val("").focus();
				$("#pw2").val("");
				return false;
			}else if ($("#pwd").val().length < 8) {
				alert("비밀번호는 8자 이상으로 설정해야 합니다.");
				$("#pwd").val("").focus();
				return false;
			}else if($.trim($("#pwd").val()) !== $("#pwd").val()){
				alert("공백은 입력이 불가능합니다.");
				return false;
			}
		}));
	})
</script>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container">
			<div class="w3-center w3-large w3-margin-top">
				<h3>마이페이지</h3>
			</div>
			<div
				id="myPage_formContainer"
			>
				<form id="myForm" action="${contextPath }/member/update_mypage.do" method="post">
					<p
						id="myPage_id"
					>
						<label>아이디</label> 
						<input class="w3-input" type="text" id="id" name="id" readonly value="${ member.id }"> 
					</p>
					<p
						id="myPage_pw"
					>
						<label>이메일</label> 
						<input class="w3-input" type="text" id="email" name="email" value="${ member.email }" required> 
					</p>
					<p class="w3-center">
						<button type="submit" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">회원정보 변경</button>
					</p>
				</form>
				<br />
				<form id="pwForm" action="../member/update_pw.do" method="post">	
					<input type="hidden" name="id" value="${ member.id }">
					<p>
						<label>기존 비밀번호</label>
						<input class="w3-input" id="old_pw" name="old_pw" type="password" required>
					</p
					>
					<p
						id="myPage_newPw"
					>
						<label>새 비밀번호</label> 
						<input class="w3-input" id="pwd" name="pwd" type="password" required>
					</p>
					<p
						id="myPage_conformPw"
					>
						<label>비밀번호 확인</label>
						<input class="w3-input" type="password" id="pw2" type="password" required>
					</p>
					<p 
						class="w3-center"
						id=""
					>
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">비밀번호 변경</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>