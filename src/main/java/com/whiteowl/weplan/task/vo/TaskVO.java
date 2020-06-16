package com.whiteowl.weplan.task.vo;

import org.springframework.stereotype.Component;

@Component("taskVO")
public class TaskVO {
	
	private int id;
	private int level;
	private String title;
	private String content;
	private String isCompleted;
	private int importance;
	private String limitDate;
	private String isTask;
	private String isDelete;
	
	public TaskVO() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getIsTask() {
		return isTask;
	}
	public void setIsTask(String isTask) {
		this.isTask = isTask;
	}
	
	
}
