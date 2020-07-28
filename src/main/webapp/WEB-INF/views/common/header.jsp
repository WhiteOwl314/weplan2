<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
 <link href="${contextPath }/resources/css/common/header.css" 
 			rel="stylesheet" type="text/css">

  <meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Dosis&display=swap" rel="stylesheet">
	<title>헤더</title>
	<style type="text/css">
		.header_container{
			display: flex;
			justify-content: space-between;
		}
		.header_container .menu1{
			display: flex;
		}
		.header_container .menu2{
			display: flex;
			margin-right: 45px;
		}
		
		.logo{
			height: 40px;
			margin-top: 2px;
		}
		.logo-a{
			 text-decoration: none;
		}
		.logo_container{
			margin-left: 11px;
		}
		.header_settings.submenu{
			width: 250px;
			height: 350px;
			background-color: red;
		}
		.task_menu_container{
			margin-left: 30px;
			margin-top: 16px;
		}
		.task_menu_a{
			text-decoration: none;
		}
		.task_menu_text{
			color: #3B3B3B;
			font-size: 15px;
		}
		.search_container{
			margin-top: 13px;
		}
		.search_div{
			border: 2px solid rgb(223, 225, 230);
			border-radius: 4px;
			height: 20px;
			
		}
		.search_div div{
			float: left;
		}
		.search_container .search_div input{
			border: none;
			margin-top: 2.3px;
			width: 180px;
		}
		.search_container .search_div input::placeholder{
			color: #6d6d6d;
		}
		.search_container .search_div input:focus{
			outline:none;
		}
		.search_container .search_div .icon {
	   		filter: opacity(.4) drop-shadow(0 0 0 #6d6d6d);
	   		margin-top: 2px;
	   		margin-left: 3px;
		}
		.settings_container{
			margin-left: 15px;
		}
		.settings_div{
			margin-top: 15px;
		}
		.settings_div img{
			filter: opacity(.57) drop-shadow(0 0 0 #000000);
		}
		
		
		
	</style>
</head>
<body>
	<div
		class="header_container"
	>
		<div
			class="menu1"
		>
			<div
				class="logo_container"
			>
				<a 
					href="${contextPath}/main.do"
					class="logo-a"
				>
						<img 
							src="${contextPath}/resources/images/logo4.png"
							class="logo"
						/>
				</a>
			</div>
			
			<div
				class="task_menu_container"
			>
				 <a href="#"
					 class="task_menu_a"
				 >
					 <div
						 class="task_menu_text"
					 >
						 작업
					 </div>
				 </a>
			</div>
		</div>
		<div
			class="menu2"
		>
			 <div
				 class="search_container"
			 >
				 <div
					 class="search_div"
				 >
					 <form>
						 <div>
							<img 
								alt="검색"
								src="${contextPath}/resources/images/search-black-18dp.svg"
								class="icon"
							/>
						 </div>
						 <div>
							<input type="text" placeholder="검색"/>
						 </div>
					 </form>
				 </div>
			 </div>

			 <div
				 class="settings_container"
			 >
				 <div
					 class="settings_div"
				 >
					<img 
						alt="설정"
						src="${contextPath}/resources/images/settings-black-18dp.svg"
						class="icon"
					/>
				 </div>
			 </div>
		</div>
	</div>
	
	<div
		class="header_settings submenu"
	>
		<div>
			<a
				href="${contextPath }/member/mypage.do"
			>
				 <img 
					 alt="회원정보"
					src="${contextPath}/resources/images/account_box-black-18dp.svg"
					class="account_img icon"
				/>
				<div>
					회원정보
				</div>
			</a>
		</div>
		<div>
		 
			<a
				href="${contextPath}/member/logout.do"
			>
				 <img 
					alt="로그아웃"
					src="${contextPath}/resources/images/logout-black-18dp.svg"
					class="logout_img icon"
				/>
				<div>
					로그아웃
				</div>
			</a>
		
		</div>
	</div>


</body>
</html>