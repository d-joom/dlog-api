package com.dlog.api.service.blog;

import java.util.List;

import com.dlog.api.dto.UserBlogCategoryDto;
import com.dlog.api.dto.UserBlogCategoryResult;
import com.dlog.api.dto.UserBlogTopCategoryDto;

public interface UserBlogCategoryService {

	public List<UserBlogCategoryResult> getUserBlogCategories();
	
	public List<UserBlogCategoryResult> getUserBlogCategoryByUserBlogId(String userBlogId);
	
	public List<UserBlogTopCategoryDto> getUserBlogTopCategoryByUserBlogId(String userBlogId);
	
	public String addUserBlogCategory(String token, UserBlogCategoryDto dto);
	
	public String modifyUserBlogCategory(String token, String uuid, UserBlogCategoryDto dto);
	
	public String deleteUserBlogCategory(String token, String uuid);
	
}
