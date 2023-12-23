package com.dlog.api.service.blog;

import java.util.Map;

import com.dlog.api.dto.UserPostDto;
import com.dlog.api.model.blog.UserPost;
import com.dlog.api.model.response.ListResult;

public interface UserPostService {

	public ListResult<UserPost> getUserPosts(String token, Map<String, Object> keyword);
	
	public String addUserPost(String token, UserPostDto dto);
	
	public String modifyUserPost(String token, String uuid, UserPostDto dto);
	
	public String deleteUserPost(String token, String uuid);
	
}
