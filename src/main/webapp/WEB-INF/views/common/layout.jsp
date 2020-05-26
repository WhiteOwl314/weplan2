<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    
    <!-- JQuery -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script> 
    <script>
    	$(document).ready(function(){
    		
    		
    		$('#inbox-button-container').click(function(){
    			
    			$("#inbox-container").toggle("fast");

				if($("#inbox-button-container").css("background-color") == "yellow"){
					$("#inbox-button-container").css("background-color", "red");
					console.log("yello");
				} else {
					$("#inbox-button-container").css("background-color", "yellow");
					console.log("red");
				}
    			
    		});
    	});
    	
    </script>
    
    <!-- Style -->
    <link href="${contextPath }/resources/css/reset.css" rel="stylesheet" type="text/css">
    <style>
      #container {
        width: 100%;
        margin: 0px auto;
          text-align:center;
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
      	width: 700px;
      	background-color: red;
      	position: fixed;
      	top: 220px;
      	right: 150px;
      	display: none;
      }
      
    </style>
    <title><tiles:insertAttribute name="title" /></title>
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
      <div id="intox-containter-parent">
		  <div id="inbox-container">
			  <form>
				  <input type="text" />
			  </form>
		  </div>
      </div>
    </div>
  </body>
</html>