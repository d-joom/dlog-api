package com.dlog.api.service.blog;

import java.util.Map;

import com.dlog.api.dto.UserBlogCategoryDto;
import com.dlog.api.model.blog.UserBlogCategory;
import com.dlog.api.model.response.ListResult;

public interface UserBlogCategoryService {

	public ListResult<UserBlogCategory> getUserBlogCategories(String token, Map<String, Object> keyword);
	
	public String addUserBlogCategory(String token, UserBlogCategoryDto dto);
	
	public String modifyUserBlogCategory(String token, String uuid, UserBlogCategoryDto dto);
	
	public String deleteUserBlogCategory(String token, String uuid);
	
}
