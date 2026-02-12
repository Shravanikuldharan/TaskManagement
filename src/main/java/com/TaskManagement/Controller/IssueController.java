package com.TaskManagement.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.DTO.UserProfileUpdateDTO;
import com.TaskManagement.Entity.Issue;
import com.TaskManagement.Entity.IssueComment;
import com.TaskManagement.Enum.IssueStatus;
import com.TaskManagement.Service.IssueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	@PostMapping("/createIssue")
	public ResponseEntity<Issue>createIssue(@RequestBody Issue issue) {
		return ResponseEntity.ok(issueService.createIssue(issue));
	}

	@PutMapping("updateStatus/{id}")
	public ResponseEntity<Issue>updateIssueStatus(@PathVariable Long id,
												 @RequestParam IssueStatus issueStatus,
												 @PathVariable String performedBy) {
		return ResponseEntity.ok(issueService.updateStatus(id, issueStatus, performedBy));
	}
	
	@PostMapping("/{id}/comments")
	public ResponseEntity<IssueComment>addComment(@PathVariable Long issueId,
												 @PathVariable String authorEmail,
												 @RequestBody Map<String, String>body) {
		String commentBody = body.get("body");
		String author = authorEmail == null? body.getOrDefault("author", "system@gmail"):authorEmail; 
		return ResponseEntity.ok(issueService.addComment(issueId, authorEmail, commentBody));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Issue>> search(@RequestParam Map<String, String> filter) {
		return ResponseEntity.ok(issueService.search(filter));
	}
}