package com.dlog.api.service.blog;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dlog.api.dto.UserInfoDto;
import com.dlog.api.dto.UserPostDto;
import com.dlog.api.model.blog.UserPost;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.SingleResult;
import com.dlog.api.repository.blog.UserPostRepository;
import com.dlog.api.specification.blog.UserPostSpecs;
import com.dlog.api.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("UserPostService")
public class UserPostServiceImpl implements UserPostService {
	
	private final UserPostRepository userPostRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public ListResult<UserPost> getUserPosts(String token, Map<String, Object> keyword) {
		
		ListResult<UserPost> result = new ListResult<>();
		try {
			List<UserPost> users = userPostRepository.findAll(UserPostSpecs.searchWith(keyword));
			result.setList(users);
		} catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setCode(400);
		}
		
		return result;
	}
	
	@Override
	public UserPost addUserPost(String token, UserPostDto dto) {
		
		try {
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserPost userPost = new UserPost();
			userPost.setUuid(UUID.randomUUID().toString());
			userPost.setTitle(dto.getTitle());
			userPost.setContents(dto.getContents());
			userPost.setTemporary(dto.getIsTemporary());
			userPost.setCreatedBy(userInfo.getEmail());
			
			userPost = userPostRepository.save(userPost);
			
			return userPost;
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public String modifyUserPost(String token, String uuid, UserPostDto dto) {
		
		try {
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserPost userPost = userPostRepository.findByUuid(uuid).orElse(null);
			
			if(userPost == null) {
				return "유효하지 않은 user post 입니다."; 
			} else {
				
				if (!userPost.getCreatedBy().equals(userInfo.getEmail())) {
					return "수정할 권한이 없습니다.";
				}
				
				UserPost result = modelMapper.map(dto, UserPost.class);
				modelMapper.map(result, userPost);
				
				userPostRepository.save(userPost);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
			return e.getMessage();
		}

		return "200";
	}
	
	@Override
	public String deleteUserPost(String token, String uuid) {
		
		try {
			
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserPost userPost = userPostRepository.findByUuid(uuid).orElse(null);
			
			if(userPost == null) {
				return "유효하지 않은 user post 입니다."; 
			} else {
				if (!userPost.getRowId().equals(userInfo.getRowId())) {
					return "삭제할 권한이 없습니다.";
				}
				userPost.setIsDeleted(true);
				userPostRepository.save(userPost);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return "200";
	}
}
