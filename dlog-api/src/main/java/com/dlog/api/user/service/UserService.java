package com.dlog.api.user.service;

import java.util.List;
import java.util.Map;

import com.dlog.api.user.Dto.CreateUserDto;
import com.dlog.api.user.model.User;

public interface UserService {

	public List<User> getUsers(Map<String, Object> keyword);
	
	public String addUser(CreateUserDto dto);
	
}
