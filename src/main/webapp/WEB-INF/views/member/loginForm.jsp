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
<c:choose>
	<c:when test="${result=='loginFailed' }">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    }
	    
	  </script>
	</c:when>
</c:choose>  



</head>

<body>
<div
	id="form_container"
>
	<div
		id="form_header"
	>
		<div
			id="form_header_title"
		>
			WEEKTASK
		</div>
	</div>
	<div
		id="form_body"
	>
		<form name="frmLogin" method="post"  action="${contextPath}/member/login2.do">
			<div
				id="form_main"
			>
				<div
					id="form_main_form"
				>
					<div
						id="form_main_id"
					>
						<input type="text" name="id" value="" size="20" placeholder="아이디">
					</div>
					<div
						id="form_main_pw"
					>
						<input type="password" name="pwd" value="" size="20" placeholder="비밀번호">
					</div>
					<div
						id="form_main_submit"
					>
						<input type="submit" value="로그인" > 
					</div>
				</div>
			</div>
			<div
				id="form_sub"
			>
			   <a
				   href="${contextPath }/member/find_id_form.do"
				   id="form_sub_findId"
			   >
				   <div title="아이디 찾기" id="find_id_btn">아이디 찾기</div>
			   </a>
			  <a
				   href="${contextPath }/member/find_pw_form.do"
				   id="form_sub_findPw"
			   >
				   <div title="비밀번호 찾기" id="find_pw_form">비밀번호 찾기</div>
			   </a>
			   
			   <a 
			   	href="${contextPath }/member/memberForm.do"
				   id="form_sub_memberForm"
			   >
			   	<div style="text-align:center">회원가입</div>
			   </a>
			</div>
		</form>
	</div>
</div>


</body>
</html>
