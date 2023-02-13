package com.file.upload.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "answer")
@Entity(name = "answer")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tagId;
	
	@Column(name = "tag_Name")
	private String tagName;
	
}
