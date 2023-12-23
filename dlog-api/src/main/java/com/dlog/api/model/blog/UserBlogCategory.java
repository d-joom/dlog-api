package com.dlog.api.model.blog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_blog_categories")
public class UserBlogCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "row_id")
	private Long rowId;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "user_blog_id")
	private String userBlogId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "depth")
	private long depth;
	
	@Column(name = "list_order")
	private long listOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private UserBlogCategory parent;

	@OneToMany(mappedBy = "parent")
	private List<UserBlogCategory> children = new ArrayList<>();

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_time_at")
	@CreationTimestamp
	private LocalDateTime createdTimeAt = LocalDateTime.now();

	@Column(name = "updated_time_at")
	@UpdateTimestamp
	private LocalDateTime updatedTimeAt = LocalDateTime.now();

	@Column(name = "is_deleted", columnDefinition = "boolean default false")
	private Boolean isDeleted = false;
	
	public void updateParent(UserBlogCategory parent){
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
