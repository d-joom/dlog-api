package com.dlog.api.service.blog;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dlog.api.dto.UserBlogDto;
import com.dlog.api.dto.UserInfoDto;
import com.dlog.api.model.blog.UserBlog;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.repository.blog.UserBlogRepository;
import com.dlog.api.specification.blog.UserBlogSpecs;
import com.dlog.api.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userBlogService")
public class UserBlogServiceImpl implements UserBlogService {
	
	private final UserBlogRepository userBlogRepository;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public ListResult<UserBlog> getUserBlogs(String token, Map<String, Object> keyword) {
		
		ListResult<UserBlog> result = new ListResult<>();
		try {
			List<UserBlog> users = userBlogRepository.findAll(UserBlogSpecs.searchWith(keyword));
			result.setList(users);
		} catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setCode(400);
		}
		
		return result;
	}
	
	@Override
	public String addUserBlog(String token, UserBlogDto dto) {
		
		try {
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserBlog userBlog = new UserBlog();
			userBlog.setUuid(UUID.randomUUID().toString());
			userBlog.setUserId(userInfo.getUserId());
			userBlog.setName(dto.getName());
			userBlog.setDescription(dto.getDescription());
			userBlog.setTheme(dto.getTheme());
			userBlog.setPavicon(dto.getPavicon());
			userBlog.setCreatedBy(userInfo.getEmail());
			
			userBlogRepository.save(userBlog);
			
			return "200";
		} catch(Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}
	
	@Override
	public String modifyUserBlog(String token, String uuid, UserBlogDto dto) {
		
		try {
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserBlog userBlog = userBlogRepository.findByUuid(uuid).orElse(null);
			
			if(userBlog == null) {
				return "유효하지 않은 user blog rowNumber 입니다."; 
			} else {
				
				if (!userBlog.getRowId().equals(userInfo.getRowId())) {
					return "수정할 권한이 없습니다.";
				}
				
				UserBlog result = modelMapper.map(dto, UserBlog.class);
				modelMapper.map(result, userBlog);
				
				userBlogRepository.save(userBlog);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return null;
	}
	
	@Override
	public String deleteUserBlog(String token, String uuid) {
		
		try {
			
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));
			
			UserBlog userBlog = userBlogRepository.findByUuid(uuid).orElse(null);
			
			if(userBlog == null) {
				return "유효하지 않은 user blog rowId 입니다."; 
			} else {
				if (!userBlog.getRowId().equals(userInfo.getRowId())) {
					return "삭제할 권한이 없습니다.";
				}
				userBlog.setIsDeleted(true);
				userBlogRepository.save(userBlog);
			}
		} catch(Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return "200";
	}
}
