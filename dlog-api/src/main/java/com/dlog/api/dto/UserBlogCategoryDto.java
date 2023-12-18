package com.dlog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBlogCategoryDto {
	
	private String userBlogId;
	
	private String name;
	
	private String description;
	
	private long depth;
	
	private String parentId;
	
}
