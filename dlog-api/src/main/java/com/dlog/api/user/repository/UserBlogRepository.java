package com.dlog.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.user.model.UserBlog;

public interface UserBlogRepository extends JpaRepository<UserBlog, Long>, JpaSpecificationExecutor<UserBlog> {
	
	Optional<UserBlog> findByUuid(String uuid);
	

}
