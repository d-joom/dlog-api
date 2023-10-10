package com.dlog.api.user.service;

import java.util.Map;

import com.dlog.api.model.response.ListResult;
import com.dlog.api.user.Dto.LoginDto;
import com.dlog.api.user.Dto.UserDto;
import com.dlog.api.user.model.User;

public interface UserService {
	
	public User login(LoginDto dto);
	
	public User getUserByUserId(String userId);

	public ListResult<User> getUsers(Map<String, Object> keyword);
	
	public String addUser(UserDto dto);
	
	public String modifyUser(String uuid, UserDto dto);
	
}
