package com.dlog.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.dlog.api.model.blog.UserBlogCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBlogCategoryResult {
	
	private long rowId;
	
	private String uuid;

    private String name;

    private long listOrder;

    private List<UserBlogCategoryResult> children;
    
    public UserBlogCategoryResult(final UserBlogCategory userBlogCategory) {
        this.rowId = userBlogCategory.getRowId();
        this.uuid = userBlogCategory.getUuid();
        this.name = userBlogCategory.getName();
        this.listOrder = userBlogCategory.getListOrder();
        this.children = userBlogCategory.getChildren().stream().map(UserBlogCategoryResult::new).collect(Collectors.toList());
    }
}
