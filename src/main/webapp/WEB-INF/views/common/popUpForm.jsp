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
				</form>
			</div>
		</div>
	</article>
</div>
<!--Popup End -->
