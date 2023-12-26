package com.dlog.api.repository.blog;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.model.blog.UserBlog;

public interface UserBlogRepository extends JpaRepository<UserBlog, Long>, JpaSpecificationExecutor<UserBlog> {
	
	Optional<UserBlog> findByUuid(String uuid);
	
	Optional<UserBlog> findByUserId(String userId);
	

}
