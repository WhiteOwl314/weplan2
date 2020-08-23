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
		.task_menu_container{
			margin-left: 30px;
			margin-top: 16px;
		}
		.task_menu_a{
			text-decoration: none;
		}
		.task_menu_text{
			font-size: 15px;
		}
		.task_menu_text a{
			text-decoration: none;
			color: #3B3B3B;
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
			cursor: pointer;
		}
		.settings_div:hover{
			filter: opacity(.57) drop-shadow(0 0 0 #000000);
		}
		.settings_div:active{
			filter: opacity(.4) drop-shadow(0 0 0 #000000);
		}
		.settings_div img{
			filter: opacity(.57) drop-shadow(0 0 0 #000000);
		}
		.header_settings.submenu{
			width: 120px;
			height: 70px;
			position: absolute;
			right: 45px;
			box-shadow: rgba(9, 30, 66, 0.25) 0px 4px 8px -2px, rgba(9, 30, 66, 0.31) 0px 0px 1px;
			z-index: 400;			
			background-color: white;
			display: none;
		}
		.submenu .menu{
			margin : 10px;
		}
		.submenu .menu .menu_margin{
			padding-top: 5px;
		}
		.submenu .menu .menu_margin:hover{
			background-color: #e2e2e2;
		}
		.submenu .menu a{
			text-decoration: none;
		}
		.submenu .menu_item{
			display: flex;
			justify-content: center;
		}
		.submenu .menu_item .menu_text{
			margin-left: 10px;
			font-size: 15px;
			color: #3B3B3B;
			margin-top: 3px;
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
					 	<a
					 		href="${contextPath}/task/listInboxTasks.do"
					 	>
					 	</a>
					 </div>
				 </a>
			</div>
		</div>
		<div
			class="menu2"
		>
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
		<div
			class="menu"
		>
			<div
				class="menu1 menu_margin"
			>
				<a
					href="${contextPath }/member/mypage.do"
				>
					<div
						class="menu_item"
					>
						<div
							class="img_container"
						>
							 <img 
								 alt="회원정보"
								src="${contextPath}/resources/images/account_box-black-18dp.svg"
								class="account_img icon"
							/>
						</div>
						<div
							class="menu_text"
						>
							회원정보
						</div>
					</div>
				</a>
			</div>
			<div
				class="menu2 menu_margin"
			>
				<a
					href="${contextPath}/member/logout.do"
				>
					 <div
						class="menu_item"
					 >
						 <div
							class="img_container"
						 >
							 <img 
								alt="로그아웃"
								src="${contextPath}/resources/images/logout-black-18dp.svg"
								class="logout_img icon"
							/>
						 </div>
						 <div
							class="menu_text"
						 >
							로그아웃
						 </div>
						 
					 </div>
				</a>
			
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$('.settings_div').click(function () {
			if($('.header_settings.submenu').css('display') == 'block'){
				$('.header_settings.submenu').css('display','none')
			} else{
				$('.header_settings.submenu').css('display','block')
			}
		})
		
	</script>

</body>
</html>