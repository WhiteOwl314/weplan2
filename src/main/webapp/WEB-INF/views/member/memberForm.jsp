<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입창</title>
	<style>
	   .text_center{
		 text-align:center;
	   }
	</style>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		$(function(){
			
			/* validation */
			$("#joinForm").submit(function(){
				if($("#pw").val() !== $("#pw2").val()){
					alert("비밀번호가 다릅니다.");
					$("#pw").val("").focus();
					$("#pw2").val("");
					return false;
				}else if ($("#pw").val().length < 8) {
					alert("비밀번호는 8자 이상으로 설정해야 합니다.");
					$("#pw").val("").focus();
					return false;
				}else if(
						$.trim($("#pw").val()) !== $("#pw").val() 
						|| $.trim($("#email").val()) !== $("#email").val() 
						|| $.trim($("#id").val()) !== $("#id").val()
				){
					alert("공백은 입력이 불가능합니다.");
					return false;
				}
			})
			
			$("#id").keyup(function() {
				$.ajax({
					url : "${contextPath}/member/check_id.do",
					type : "POST",
					data : {
						id : $("#id").val()
					},
					success : function(result) {
						if (result == 1) {
							$("#id_check").html("중복된 아이디가 있습니다.");
							$("#joinBtn").attr("disabled", "disabled");
						} else {
							$("#id_check").html("");
							$("#joinBtn").removeAttr("disabled");
						}
					},
				})
			});
			
			$("#email").keyup(function(){
				$.ajax({
					url : "${contextPath}/member/check_email.do",
					type : "POST",
					data : {
						email : $("#email").val()
					},
					success : function(result) {
						if (result == 1) {
							$("#email_check").html("중복된 이메일이 있습니다.");
						} else {
							$("#email_check").html("");
						}
					},
				})
			});
		})
	</script>


	
</head>
<body>
	<div>
		<div>
			<div>
				<h3>Member Join Form</h3>
			</div>
			<div>
				<form id="joinForm" action="${contextPath}/member/join_member.do" method="post">
					<p>
						<label>ID</label> 
						<input type="text" id="id" name="id" required> 
						<span id="id_check"></span>
					</p>
					<p>
						<label>Password</label> 
						<input id="pw" name="pwd" type="password" required>
					</p>
					<p>
						<label>Confirm</label> 
						<input id="pw2" type="password" required>
					</p>
					<p>
						<label>Email</label>
						<input type="text" id="email" name="email" required placeholder="이메일 인증 후 로그인이 가능합니다.">
						<span id="email_check"></span>
					</p>
					<p>
						<label>Name</label>
						<input type="text" id="name" name="name" required>
					</p>
					<p>
						<button type="submit" id="joinBtn">Join</button>
						<button type="button" onclick="history.go(-1);">Cancel</button>
					</p>
				</form>
			</div>
		</div>
	</div>
	
</body>
