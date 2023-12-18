package com.dlog.api.model.blog;

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
@Table(name = "user_posts")
public class UserPost {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id")
	private Long rowId;
	
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "userBlogId")
	private String userBlogId;
	
	@Column(name = "userBlogCategoryId")
	private String userBlogCategoryId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "is_temporary")
	private boolean isTemporary;
	
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
