<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!--Popup Start -->
<div 
	id="layerbox" 
	class="layerpop"
	style="width: 700px; height: 450px;"
>
	<article class="layerpop_area">
		<div class="title">수정</div>
		<div class="content">
			<div
				class="layerpop_content"
			>
				<form 
					action="${contextPath }/goal/updateGoal.do"
					method="post"
					class="layerpop_form"
				>
					<div
						class="form_main"
					>
						<div
							class="layerpop_form_container importance_container"
						>
							<div
								class="layerpop_form_title"
							>
								중요도
							</div>
							<div
								class="layerpop_importance"
							>
								<label><input type="radio" name="importance" value="1" class="importance importance1">상</label>
								<label><input type="radio" name="importance" value="2" class="importance importance2">중</label>
								<label><input type="radio" name="importance" value="3" class="importance importance3">하</label>
							</div>
						</div>
						<div
							class="layerpop_form_container title_container"
						>
							<div
								class="layerpop_form_title"
							>
								제목
							</div>
							<input class="layerpop_title" type="text" name="title" >
						</div>
						<div
							id="layerpop_absoluteValueId"
							class="layerpop_connectionForm"
						>
							<label for="layerpop_absoluteValueId_select">Absolute Value:</label>
							<select id="layerpop_absoluteValueId_select" name="absolute_value_id">
								<option value="0">없음</option>
							</select>
						</div>
						<div
							id="layerpop_projectId"
							class="layerpop_connectionForm"
						>
							<label for="layerpop_projectId_select">Project:</label>
							<select id="layerpop_projectId_select" name="goal_id">
								<option value="0">없음</option>
							</select>
						</div>
						<div
							id="layerpop_goalId"
							class="layerpop_connectionForm"
						>
							<label for="layerpop_goalId_select">Goal:</label>
							<select id="layerpop_goalId_select" name="yearlyPlan_id">
								<option value="0">없음</option>
							</select>
						</div>
						<div
							class="layerpop_form_container startDate_container"
						>
							<div
								class="layerpop_startDate_container"
							>
								<div
									class="layerpop_form_title"
								>
									시작시간
								</div>
								<input 
									type="text" 
									name="startDate" 
									class="layerpop_startDate_form"
								/>
								<input 
									type="text" 
									name="startTime" 
									class="layerpop_Time_form"
								/>
							</div>
						</div>
						<div
							class="layerpop_form_container limitDate_container"
						>
							<div
								class="layerpop_limitDate_container"
							>
								<div
									class="layerpop_form_title"
								>
									마감시간
								</div>
								<input 
									type="text" 
									name="limitDate" 
									class="layerpop_limitDate_form"
								/>
								<input 
									type="text" 
									name="limitTime" 
									class="layerpop_Time_form"
								/>
							</div>
						</div>
						<div
							class="layerpop_form_container month_container"
						>
							<div
								class="layerpop_month_container"
							>
								<div
									class="layerpop_form_title"
								>
									마감 달
								</div>
								<input 
									type="text" 
									name="month" 
									class="layerpop_month_form"
								/>
							</div>
						</div>
						<div
							class="layerpop_form_container content_container"
						>
							<div
								class="layerpop_form_title"
							>
								내용
							</div>
							<div>
								<textarea 
									name="content"
									class="layerpop_content"
									id="layerpop_form_content"
								></textarea>
							</div>
						</div>
					</div>
					<div
						class="form_button"
					>
						<div
							class="layerpop_form_container layerpop_submit "
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
					<input type="hidden" name="id" class="layerpop_id">
					<input id="layerpop_week" type="hidden" name="week">
				</form>
			</div>
		</div>
	</article>
</div>
<!--Popup End -->
