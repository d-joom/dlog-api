package com.dlog.api.repository.blog;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dlog.api.model.blog.UserPost;

public interface UserPostRepository extends JpaRepository<UserPost, Long>, JpaSpecificationExecutor<UserPost> {
	
	Optional<UserPost> findByUuid(String uuid);
	

}
