package com.whiteowl.weplan.monthlyplan.vo;

import org.springframework.stereotype.Component;

@Component("monthlyPlanVO")
public class MonthlyPlanVO {
	
	private int id;
	private String title;
	private String content;
	private String isCompleted;
	private int importance;
	private String month;
	private String isDelete;
	private String member_id;
	private int yearlyPlan_id;

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
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYearlyPlan_id() {
		return yearlyPlan_id;
	}
	public void setYearlyPlan_id(int yearlyPlan_id) {
		this.yearlyPlan_id = yearlyPlan_id;
	}
	
	

}
