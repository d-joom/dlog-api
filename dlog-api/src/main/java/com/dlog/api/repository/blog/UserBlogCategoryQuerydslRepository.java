package com.dlog.api.repository.blog;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dlog.api.model.blog.QUserBlogCategory;
import com.dlog.api.model.blog.UserBlogCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class UserBlogCategoryQuerydslRepository {
	
	 private final JPAQueryFactory query;
	 private QUserBlogCategory parent = new QUserBlogCategory("parent");
	 private QUserBlogCategory child = new QUserBlogCategory("child");

	    public List<UserBlogCategory> findAllWithQuerydsl() {

	        return query.selectFrom(parent)
	                .distinct()
	                .leftJoin(parent.children, child)
	                .fetchJoin()
	                .where(
	                        parent.parent.isNull()
	                )
	                .orderBy(parent.listOrder.asc(), child.listOrder.asc())
	                .fetch();
	    }
	    
	    public List<UserBlogCategory> findAllWithQuerydslByCreatedBy(String email) {

	        return query.selectFrom(parent)
	                .distinct()
	                .leftJoin(parent.children, child)
	                .fetchJoin()
	                .where(
	                        parent.parent.isNull(),
	                        parent.createdBy.eq(email)
	                )
	                .orderBy(parent.listOrder.asc(), child.listOrder.asc())
	                .fetch();
	    }
	    
}
