package com.whiteowl.weplan.goal.vo;

import org.springframework.stereotype.Component;

@Component("goalVO")
public class GoalVO {
	
	private int id;
	private String title;
	private String content;
	private String isCompleted;
	private int importance;
	private String limitDate;
	private String startDate;
	private String member_id;
	private int absolute_value_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getAbsolute_value_id() {
		return absolute_value_id;
	}
	public void setAbsolute_value_id(int absolute_value_id) {
		this.absolute_value_id = absolute_value_id;
	}
	
	
	
	

}
