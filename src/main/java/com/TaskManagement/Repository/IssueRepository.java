package com.TaskManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TaskManagement.Entity.Issue;
import com.TaskManagement.Enum.IssueStatus;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
	List<Issue>findByAssigneeEmail(String assigneeEmail);
	List<Issue>findBySprintId(Long sprintId);
	List<Issue>findByEpicId(Long epicId);
	List<Issue>findByIssueStatus(IssueStatus status);
	List<Issue>findByIssueKey(String issueKey);
}