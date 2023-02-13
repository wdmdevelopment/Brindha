package com.file.upload.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "answer")
@Entity(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long answerId;
	
	@Column(name = "user_id")
	@OneToMany(mappedBy = "Answer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@Column(name = "Answer")
	private String answerDescription;
	
	@Column(name = "answer_created")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dateTimeCreated;
}
