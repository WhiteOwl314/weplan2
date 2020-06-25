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
<title>로그인창</title>
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
<form name="frmLogin" method="post"  action="${contextPath}/member/login2.do">
   <table border="1"  width="80%" align="center" >
      <tr align="center">
         <td>아이디</td>
         <td>비밀번호</td>
      </tr>
      <tr align="center">
         <td>
	    <input type="text" name="id" value="" size="20">
	 </td>
         <td>
	    <input type="password" name="pwd" value="" size="20">
	 </td>
      </tr>
      <tr align="center">
         <td colspan="2">
            <input type="submit" value="로그인" > 
         </td>
      </tr>
   </table>
   	<a
   		href="${contextPath }/member/find_id_form.do"
   	>
   		<span title="아이디 찾기" id="find_id_btn">아이디 찾기</span>
   	</a>
   	<a href="${contextPath }/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>
</form>


</body>
</html>
