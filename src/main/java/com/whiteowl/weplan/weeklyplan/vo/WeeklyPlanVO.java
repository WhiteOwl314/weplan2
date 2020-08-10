package com.whiteowl.weplan.weeklyplan.vo;

import org.springframework.stereotype.Component;

@Component("weeklyPlanVO")
public class WeeklyPlanVO {

	private int id;
	private String title;
	private String content;
	private String isCompleted;
	private int importance;
	private String month;
	private int week;
	private String startDate;
	private String limitDate;
	private String isDelete;
	private String member_id;
	private int yearly_plan_id;

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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
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
	public int getYearly_plan_id() {
		return yearly_plan_id;
	}
	public void setYearly_plan_id(int yearly_plan_id) {
		this.yearly_plan_id = yearly_plan_id;
	}
	

}
