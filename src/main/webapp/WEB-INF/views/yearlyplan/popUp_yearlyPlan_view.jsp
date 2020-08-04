<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div
	class="popUp_yearlyPlan_view"
>
	<form 
		action="${contextPath }/yearlyPlan/updateYearlyPlan.do"
		method="post"
		class="yearlyPlan_form"
	>
		<div
			class="form_main"
		>
			<div
				class="yearlyPlan_container"
			>
				<div
					class="yearlyPlan_form_title"
				>
					중요도
				</div>
				<div
					class="yearlyPlan_importance"
				>
					<label><input type="radio" name="importance" value="1" class="importance importance1">상</label>
					<label><input type="radio" name="importance" value="2" class="importance importance2">중</label>
					<label><input type="radio" name="importance" value="3" class="importance importance3">하</label>
				</div>
			</div>
			<div
				class="yearlyPlan_container"
			>
				<div
					class="form_title"
				>
					제목
				</div>
				<input class="yearlyPlan_title" type="text" name="title" >
			</div>
			<div
				class="yearlyPlan_container"
			>
				<div
					class="form_title"
				>
					시작시간
				</div>
			</div>
			<div
				class="yearlyPlan_container"
			>
				<div
					class="form_title"
				>
					마감시간
				</div>
			</div>
			<div
				class="yearlyPlan_container"
			>
				<div
					class="form_title"
				>
					내용
				</div>
				<div>
					<textarea 
						name="content"
						class="yearlyPlan_content"
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
				class="yearlyPlant_container yearlyPlan_submit"
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
		<input type="hidden" name="id" class="yearlyPlan_id">
	</form>
</div>