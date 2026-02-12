package com.TaskManagement.Entity;

import com.TaskManagement.Enum.IssueStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "subTasks")
public class SubTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long parentIssueId;
	private String issueTitle;
	
	private IssueStatus issueStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentIssueId() {
		return parentIssueId;
	}

	public void setParentIssueId(Long parentIssueId) {
		this.parentIssueId = parentIssueId;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public IssueStatus getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}

}