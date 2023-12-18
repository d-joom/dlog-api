package com.dlog.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBlogCategoryResult {
	
	private long row_number;

    private String name;

    private int listOrder;

    private List<UserBlogCategoryResult> children;
}
