package com.dlog.api.user.service;

import java.util.Map;

import com.dlog.api.model.response.ListResult;
import com.dlog.api.user.Dto.UserBlogDto;
import com.dlog.api.user.model.UserBlog;

public interface UserBlogService {

	public ListResult<UserBlog> getUserBlogs(String token, Map<String, Object> keyword);
	
	public String addUserBlog(String token, UserBlogDto dto);
	
	public String modifyUserBlog(String token, String uuid, UserBlogDto dto);
	
	public String deleteUserBlog(String token, String uuid);
	
}
