package com.dlog.api.user.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlog.api.user.Dto.CreateUserDto;
import com.dlog.api.user.model.User;
import com.dlog.api.user.repository.UserRepository;
import com.dlog.api.user.specification.UserSpecs;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers(Map<String, Object> keyword) {
		List<User> users = userRepository.findAll(UserSpecs.searchWith(keyword));
		return users;
	}
	
	@Override
	public String addUser(CreateUserDto dto) {
		
		try {
			User user = new User();
			user.setUuid(UUID.randomUUID().toString());
			user.setId(dto.getId());
			user.setPassword(dto.getPassword());
			user.setName(dto.getName());
			user.setNickNmae(dto.getNickNmae());
			user.setEmail(dto.getEmail());
			user.setMobile(dto.getMobile());
			user.setGender(dto.getGender());
			
			userRepository.save(user);
			
			return "200";
		} catch(Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}
}
