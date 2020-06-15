<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<!DOCTYPE html>
<html lang="ko-KR">
  <head>
    <meta charset="UTF-8">
    
    <!-- JQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
    <!-- Style -->
    <link href="${contextPath }/resources/css/reset.css" rel="stylesheet" type="text/css">
    <style>
      #container {
        width: 100%;
        margin: 0px auto;
        border: 0px solid #bcbcbc;
      }
      #header {
      	height: 50px;
        border-bottom: 1px solid #DCDCDC;
      }
      #sidebar-left {
        width: 60px;
        height:700px;
        margin-right: 5px;
        margin-bottom: 5px;
        float: left;
         background-color: yellow;
        border: 0px solid #bcbcbc;
        font-size:10px;
      }
      #content {
        width: 75%;
        padding: 5px;
        margin-right: 5px;
        float: left;
        border: 0px solid #bcbcbc;
      }
      #footer {
        clear: both;
        padding: 5px;
        border: 0px solid #bcbcbc;
         background-color: lightblue;
      }
      #inbox-button-container{
      	background-color: red;
      	position: fixed;
      	bottom: 25px;
      	right: 30px;
      	height: 60px;
      	width: 60px;
      	cursor: pointer;
      }
      #inbox-container{
      	height: 100px;
      	width: 900px;
      	background-color: red;
      	position: fixed;
      	top: 220px;
      	right: 150px;
      	display: none;
      }
      
    </style>
    <title><tiles:insertAttribute name="title" /></title>
    
    <!-- TilePicker -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
  </head>
    <body>
    <div id="container">
      <div id="header">
         <tiles:insertAttribute name="header"/>
      </div>
      <div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      </div>
      <div id="content">
          <tiles:insertAttribute name="body"/>
      </div>
      <div id="footer">
          <tiles:insertAttribute name="footer"/>
      </div>
      <div id="inbox-button-container" >
      	<div id="inbox-button">
      	</div>
      </div>
      <div id="inbox-containter-parent">
		  <div id="inbox-container">
			  <form action="${contextPath }/task/addInboxTask.do" method="post">
				  <label><input type="radio" name="importance" value="1">상</label>
      			  <label><input type="radio" name="importance" value="2">중</label>
      			  <label><input type="radio" name="importance" value="3">하</label>
				  <input type="text" name="title" placeholder="title"/>
				  <input type="text" name="content" placeholder="content"/>
				  <input id="date" type="hidden" name="date"/>
				  <input id="time" type="hidden" name="time"/>
				  <input id="due" type="button" name="due" value="due">
				  <input id="nullDate" type="button" name="nullDate" value="nullDate">
				  <input type="submit" value="save"/>
			  </form>
			  


		  </div>
      </div>
    </div>

    <script>
    	$(document).ready(function(){
    		
    		/* inbox 버튼 toggle */
    		$('#inbox-button-container').click(function(){
    			$("#inbox-container").toggle("fast");
				$("#inbox-button-container").css("background-color", "yellow");
    		});
    		
    		/* due 버튼 */
    		$('#due').click(function(){
    			$("#date").attr("type","date");
    			$("#time").attr("type","time");
    			/* 현재시간으로 */
				document.getElementById('date').valueAsDate = new Date();
    			$("#time").attr("value","18:00");
    		});
    		
    		
    		/* nullDate 버튼 */
    		$('#nullDate').click(function(){
    			$("#date").attr("type","hidden");
    			$("#time").attr("type","hidden");
    			$("#date").attr("value","0000-00-00");
    			$("#time").attr("value","00:00");
    		});

    	});
    	
    </script>
    
  </body>
</html>