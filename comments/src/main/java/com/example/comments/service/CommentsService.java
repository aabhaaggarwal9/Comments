package com.example.comments.service;

import java.util.List;

import com.example.comments.entity.Comments;
import com.example.comments.payload.CommentPayload;

public interface CommentsService {

	List<Comments> getAllComments();

	List<Comments> getCommentsByUsername(String username);

	List<Comments> getCommentsByDate(String date);

	String addComment(CommentPayload comment);

	String updateComment(CommentPayload comment);

	String deleteComment(Long id);

}
