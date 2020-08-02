<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div
	class="inbox_add"
>
	<form 
		action="${contextPath }/task/addInboxTask.do"
		method="post"
		class="inbox_form"
	>
		<div
			class="form_main"
		>
			<div
				class="inbox_container"
			>
				<div
					class="inbox_form_title"
				>
					중요도
				</div>
				<div
					class="inbox_importance"
				>
					<label><input type="radio" name="importance" value="1" class="importance importance1" checked="checked">상</label>
					<label><input type="radio" name="importance" value="2" class="importance importance2">중</label>
					<label><input type="radio" name="importance" value="3" class="importance importance3">하</label>
				</div>
			</div>
			<div
				class="inbox_container"
			>
				<div
					class="form_title"
				>
					제목
				</div>
				<input class="inbox_title" type="text" name="title" >
			</div>
			<div
				class="inbox_container"
			>
				<div
					class="form_title"
				>
					시작시간
				</div>
			</div>
			<div
				class="inbox_container"
			>
				<div
					class="form_title"
				>
					마감시간
				</div>
			</div>
			<div
				class="inbox_container"
			>
				<div
					class="form_title"
				>
					내용
				</div>
				<div>
					<textarea 
						name="content"
						class="inbox_content"
					></textarea>
				</div>
			</div>
			<div>
					  <input id="date" type="hidden" name="date"/>
					  <input id="time" type="hidden" name="time"/>
					  <input id="due" type="button" name="due" value="due">
					  <input id="nullDate" type="hidden" name="nullDate" value="x">
			</div>
		</div>
		<div
			class="form_button"
		>
			<div
				class="inbox_container inbox_submit"
			>
				<input type="submit" value="저장"/>
			</div>
			<div
				class="cancle layerpop_close"
				onclick="javascript:popupClose();"
			>
				<div
					class="cancle_text"
				>
					취소
				</div>
			</div>
		</div>
		<input type="hidden" name="member_id" value="${ member.id }">
	</form>
</div>