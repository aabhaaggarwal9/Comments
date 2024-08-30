package com.example.comments.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.comments.entity.Comments;
import com.example.comments.exception.CommentsException;
import com.example.comments.payload.CommentPayload;
import com.example.comments.repository.CommentsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

	private CommentsRepository commentsRepository;

	@Override
	public List<Comments> getAllComments() {
		List<Comments> comments = commentsRepository.findAll();
		return comments;
	}

	@Override
	public List<Comments> getCommentsByUsername(String username) {
		List<Comments> comments = commentsRepository.findByUsername(username);
		return comments;
	}

	@Override
	public List<Comments> getCommentsByDate(String date) {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			LocalDate formattedDate = LocalDate.parse(date, formatter);
			List<Comments> comments = commentsRepository.findByDateOfComment(formattedDate);
			return comments;
		} catch (DateTimeParseException e) {
			throw new CommentsException(HttpStatus.BAD_REQUEST, "Invalid Date format");
		}

	}

	@Override
	public String addComment(CommentPayload comment) {
		if (comment.getUsername() == null || comment.getUsername().trim().equals("") || comment.getText() == null
				|| comment.getText().trim().equals("")) {
			throw new CommentsException(HttpStatus.BAD_REQUEST, "Please fill required fields");
		} else {
			Comments newComment = new Comments();
			newComment.setUsername(comment.getUsername());
			newComment.setText(comment.getText());
			newComment.setDateOfComment(LocalDateTime.now());

			commentsRepository.save(newComment);
			return "Comment added successfully";
		}
	}

	@Override
	public String updateComment(CommentPayload comment) {
		if (comment.getId() == null || comment.getText() == null || comment.getText().trim().equals("")) {
			throw new CommentsException(HttpStatus.BAD_REQUEST, "Please fill required fields");
		} else {
			Comments existingComment = commentsRepository.findById(comment.getId())
					.orElseThrow(() -> new CommentsException(HttpStatus.NOT_FOUND,
							"Comment doesn't exist with id: " + comment.getId()));
			existingComment.setText(comment.getText());
			existingComment.setDateOfComment(LocalDateTime.now());

			commentsRepository.save(existingComment);
			return "Comment updated successfully";
		}
	}

	@Override
	public String deleteComment(Long id) {
		Comments existingComment = commentsRepository.findById(id)
				.orElseThrow(() -> new CommentsException(HttpStatus.NOT_FOUND, "Comment doesn't exist with id: " + id));
		commentsRepository.delete(existingComment);
		return "Comment deleted successfully";
	}

}
