<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
	<div
		class="content"
		id="absoluteValueList_container"
	>
		<div
			class="header"
		>
			
			<div
				class="title"
			>
				Absolute Value
			</div>
			<div
				class="menu"
			>
				<div 
					class="add"
				>
					<div
						  class="text"
					>
						추가
					</div>
				</div>
				<div 
					class="update"
				>
					<div
						  class="text"
					>
						수정
					</div>
				</div>
				<div 
					class="update_on"
				>
					<div
						  class="text"
					>
						수정
					</div>
				</div>
				
			</div>

		</div>

		<div
			class="absoluteValues"
		>
			<c:forEach var="absoluteValue" items="${absoluteValueList }">
				<div 
					class="absoluteValue_content absoluteValue_container absoluteValue"
				>
					<div
						onMouseOver = " window.status = '${contextPath }/absoluteValue/absoluteValueView.do?id=${absoluteValue.id}'" 
						onMouseOut = " window.status = '' "
						style="cursor:pointer;"
						class="absoluteValue-item-container event"
						id="${absoluteValue.id }"
					>
						<div
							class="item"
							id="${absoluteValue.id }"
						>
							<div
								class="text"
							>
							</div>
							<script>
								//을를 구분
								function reulReturner(label) {

									 var strGA = 44032; //가
									 var strHI = 55203; //힣

									 var lastStrCode = label.charCodeAt(label.length-1);
									 var prop=true;
									 var msg;

									 if(lastStrCode < strGA || lastStrCode > strHI) {
									  return false; //한글이 아님
									 }

									 if (( lastStrCode - strGA ) % 28 == 0) prop = false;

									 if(prop) {
									  msg = label+'을';
									 }
									 else {
									  msg = label+'를';
									 }

									 return msg;
								}	

								document.write('나는 '+reulReturner('${absoluteValue.title }')+' 위해 살겠다');
							</script> 
						</div>
					</div>
					<div
						class="item-delete delete"
					>
						<a 
							href="${contextPath }/absoluteValue/deleteAbsoluteValue.do?id=${absoluteValue.id}"
						>
							<img 
								alt="delete" 
								src="${contextPath }/resources/images/highlight_off-black-18dp.svg"
								class="icon"
							>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</div>