package com.file.upload.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "question")
@Entity(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long questionId;
	
	@Column(name = "user_id")
	@OneToMany(mappedBy = "Question", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "Question")
	private String quesDescription;
	
	@Column(name = "question_created")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dateTimeCreated;
	
	@Column(name = "answer_id")
	@OneToMany(mappedBy = "Question", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Answer answer;
	
	@Column(name = "tag_id")
	@OneToMany(mappedBy = "Question", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Tag tag;
}
