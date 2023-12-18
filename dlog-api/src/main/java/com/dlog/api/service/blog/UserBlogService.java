package com.dlog.api.service.blog;

import java.util.Map;

import com.dlog.api.dto.UserBlogDto;
import com.dlog.api.model.blog.UserBlog;
import com.dlog.api.model.response.ListResult;

public interface UserBlogService {

	public ListResult<UserBlog> getUserBlogs(String token, Map<String, Object> keyword);
	
	public String addUserBlog(String token, UserBlogDto dto);
	
	public String modifyUserBlog(String token, String uuid, UserBlogDto dto);
	
	public String deleteUserBlog(String token, String uuid);
	
}
