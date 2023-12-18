package com.dlog.api.service.user;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dlog.api.dto.LoginDto;
import com.dlog.api.dto.UserDto;
import com.dlog.api.dto.UserInfoDto;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.user.User;
import com.dlog.api.repository.user.UserRepository;
import com.dlog.api.specification.user.UserSpecs;
import com.dlog.api.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public User login(LoginDto dto) {
		User user = userRepository.findByUserIdAndIsDeletedFalse(dto.getUserId()).orElse(null);
		
		if(user != null) {
			if(user.getPassword().equals(dto.getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public User getUserByUserId(String userId) {
		return userRepository.findByUserIdAndIsDeletedFalse(userId).orElse(null);
	}
	
	@Override
	public ListResult<User> getUsers(String token, Map<String, Object> keyword) {
		
		ListResult<User> result = new ListResult<>();
		try {
			List<User> users = userRepository.findAll(UserSpecs.searchWith(keyword));
			result.setList(users);
		} catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setCode(400);
		}
		
		return result;
	}
	
	@Override
	public String addUser(UserDto dto) {
		
		try {
			User user = new User();
			user.setUuid(UUID.randomUUID().toString());
			user.setUserId(dto.getUserId());
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
	
	@Override
	public String modifyUser(String uuid, UserDto dto) {
		
		try {
			User user = userRepository.findByUuid(uuid).orElse(null);
			
			if(user == null) {
				return "유효하지 않은 user rowId 입니다."; 
			} else {
				User result = modelMapper.map(dto, User.class);
				modelMapper.map(result, user);
				
				userRepository.save(user);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return null;
	}
	
	@Override
	public String deleteUser(String token, String uuid) {
		
		try {
			User user = userRepository.findByUuid(uuid).orElse(null);
			UserInfoDto info = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			if(user == null) {
				return "유효하지 않은 user rowId 입니다."; 
			} else {
				if (!user.getRowId().equals(info.getRowId())) {
					return "삭제할 권한이 없습니다.";
				}
				user.setIsDeleted(true);
				userRepository.save(user);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return "200";
	}
}
