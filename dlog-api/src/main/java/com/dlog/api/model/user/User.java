package com.dlog.api.model.user;

import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
	private Long rowId;
	
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "picture")
	private String picture;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "password_last_modify_time_at")
	private Date passwordLastModifyTimeAt;
	
	@Column(name = "last_used_time_at")
	private Date lastUsedTimeAt;
	
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
