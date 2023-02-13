package com.file.upload.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "answer_vote")
@Entity(name = "answer_vote")
public class AnswerVote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long answerVoteId;

	@Column(name = "user_id")
	@OneToMany(mappedBy = "AnswerVote", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "up_votes")
	private long upVotes;
	
	@Column(name = "down_votes")
	private long downVotes;
	
	
}
