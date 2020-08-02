<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div
	class="absoluteValue_update"
>
	<form 
		action="${contextPath }/absoluteValue/updateAbsoluteValue.do"
		method="post"	
		class="absoluteValue_form absoluteValue_update"
	>
		<div
			class="container"
		>
			<div
				class="form_title"
			>
				중요도
			</div>
			<div
				class="item_container form_importance"
			>
				<label><input type="radio" name="importance" value="1" class="importance importance1">상</label>
				<label><input type="radio" name="importance" value="2" class="importance importance2">중</label>
				<label><input type="radio" name="importance" value="3" class="importance importance3">하</label>
			</div>
		</div>
		<div
			class="container"
		>
			<div
				class="form_title"
			>
				제목
			</div>
			<input id="popUp-title" class="absoluteValue_add_title form_content_title" type="text" name="title" >
		</div>
		<div
			class="container"
		>
			<div
				class="form_title"
			>
				내용
			</div>
			<div>
				<textarea 
					id="popUp-content" 
					name="content"
					class="absoluteValue_form-content"
				></textarea>
			</div>
		</div>
		<div
			class="container absoluteValue_add_submit"
		>
			<input type="submit" value="저장"/>
		</div>
		<div
			class="absoluteValue_form cancle layerpop_close"
			onclick="javascript:popupClose();"
		>
			<div
				class="absoluteValue_cancle absoluteValue_text cancle_text"
			>
				취소
			</div>
		</div>
		<input type="hidden" name="member_id" value="${ member.id }">
		<input type="hidden" name="id" id="popUp-id">
	</form>
	
</div>