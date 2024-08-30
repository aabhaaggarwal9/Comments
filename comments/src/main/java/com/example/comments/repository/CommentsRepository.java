package com.example.comments.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.comments.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

	@Query("SELECT e FROM Comments e WHERE DATE(e.dateOfComment) = :date")
	List<Comments> findByDateOfComment(@Param("date") LocalDate formattedDate);

	List<Comments> findByUsername(String username);

}
