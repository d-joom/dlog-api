package com.dlog.api.repository.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dlog.api.dto.UserBlogTopCategoryDto;
import com.dlog.api.model.blog.UserBlogCategory;

public interface UserBlogCategoryRepository
		extends JpaRepository<UserBlogCategory, Long>, JpaSpecificationExecutor<UserBlogCategory> {

	Optional<UserBlogCategory> findByRowId(Long rowId);
	
	Optional<UserBlogCategory> findByUuid(String uuid);
	
	@Query("SELECT NEW com.dlog.api.dto.UserBlogTopCategoryDto(c.rowId, c.uuid, c.name, COUNT(p.uuid)) " +
		       "FROM UserBlogCategory c " +
		       "LEFT JOIN UserPost p ON c.uuid = p.userBlogCategoryId AND p.isDeleted = false " +
		       "WHERE c.createdBy = :userId " +
		       "GROUP BY c.uuid, c.name, c.rowId " +
		       "ORDER BY COUNT(p.uuid) DESC")
	List<UserBlogTopCategoryDto> getBlogCategoryWithPostCount(@Param("userId") String userId, Pageable pageable);
	
}
