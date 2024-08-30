package com.example.comments.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.comments.entity.Comments;
import com.example.comments.payload.CommentPayload;
import com.example.comments.service.CommentsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v2/comments")
@AllArgsConstructor
public class CommentsController {

	private CommentsService commentsService;

	@GetMapping
	public ResponseEntity<List<Comments>> getAllComments() {
		List<Comments> comments = commentsService.getAllComments();
		return ResponseEntity.ok(comments);
	}

	@GetMapping("search/username")
	public ResponseEntity<List<Comments>> getCommentsByUsername(@RequestParam("username") String username) {
		List<Comments> comments = commentsService.getCommentsByUsername(username);
		return ResponseEntity.ok(comments);
	}

	@GetMapping("search/date")
	public ResponseEntity<List<Comments>> getCommentsByDate(@RequestParam("date") String date) {
		List<Comments> comments = commentsService.getCommentsByDate(date);
		return ResponseEntity.ok(comments);
	}

	@PostMapping
	public ResponseEntity<String> addComment(@RequestBody CommentPayload comment) {
		String response = commentsService.addComment(comment);
		return ResponseEntity.ok(response);

	}

	@PutMapping
	public ResponseEntity<String> updateComment(@RequestBody CommentPayload comment) {
		String response = commentsService.updateComment(comment);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteComment(@RequestParam("id") Long id) {
		String response = commentsService.deleteComment(id);
		return ResponseEntity.ok(response);
	}

}
