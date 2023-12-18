package com.dlog.api.repository.blog;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.model.blog.UserBlogCategory;

public interface UserBlogCategoryRepository
		extends JpaRepository<UserBlogCategory, Long>, JpaSpecificationExecutor<UserBlogCategory> {

	Optional<UserBlogCategory> findByUuid(String uuid);
	

}
