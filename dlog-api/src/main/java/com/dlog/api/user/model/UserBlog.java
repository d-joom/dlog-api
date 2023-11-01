package com.dlog.api.user.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_blogs")
public class UserBlog {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_number")
	private Long rowNumber;
	
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "theme")
	private String theme;
	
	@Column(name = "pavicon")
	private String pavicon;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "created_time_at")
	@CreationTimestamp
	private LocalDateTime createdTimeAt = LocalDateTime.now();
	
	@Column(name = "updated_time_at")
	@UpdateTimestamp 
	private LocalDateTime updatedTimeAt= LocalDateTime.now();
	
	@Column(name = "is_deleted", columnDefinition = "boolean default false")
	private Boolean isDeleted = false;
}
