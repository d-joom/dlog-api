package com.dlog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBlogCategoryDto {
	
	private String name;
	
	private String uuid;
	
	private String description;
	
	private Long depth;
	
	private Long parentId;
	
}
