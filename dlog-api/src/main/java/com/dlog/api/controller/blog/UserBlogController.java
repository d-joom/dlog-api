package com.dlog.api.controller.blog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlog.api.dto.UserBlogDto;
import com.dlog.api.model.blog.UserBlog;
import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.SingleResult;
import com.dlog.api.repository.blog.UserBlogRepository;
import com.dlog.api.service.ResponseService;
import com.dlog.api.service.blog.UserBlogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Api(tags = {"user-blogs-controller"})
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UserBlogController {
	
	private final UserBlogService userBlogService;
	private final ResponseService responseService;
	private final UserBlogRepository userBlogRepository;	

	@ApiOperation(value = "블로그 specification 조회", notes = "블로그 specification 조회")
	@GetMapping("/blogs")
	public ListResult<UserBlog> getUserBlogs(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@RequestParam(required = false, defaultValue = "0") long rowNumber,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String theme,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) Boolean isDeleted ) {
		
		Map<String, Object> searchKeyword = new HashMap<>();
		
		if (rowNumber != 0) {
			searchKeyword.put("rowNumber", rowNumber);
        }
        if (uuid != null) {
			searchKeyword.put("uuid", uuid);
        }
        if (userId != null) {
			searchKeyword.put("userId", userId);
        }
        if (name != null) {
			searchKeyword.put("name", name);
        }
        if (description != null) {
			searchKeyword.put("description", description);
        }
        if (theme != null) {
			searchKeyword.put("theme", theme);
        }
        if (createdBy != null) {
			searchKeyword.put("createdBy", createdBy);
        }
        if (isDeleted != null) {
			searchKeyword.put("isDeleted", isDeleted);
        }
        
        return userBlogService.getUserBlogs(token, searchKeyword);
        
	}
	
	@ApiOperation(value = "블로그 단일 조회", notes = "블로그 단일 조회")
	@GetMapping("/blog/{uuid}")
	public SingleResult<UserBlog> getUserByUuid(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		return responseService.getSingleResult(userBlogRepository.findByUuid(uuid).orElse(null));
	}
	
	@ApiOperation(value = "블로그 생성", notes = "블로그 생성")
	@PostMapping("/blog")
	public CommonResult addUserBlog(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@RequestBody UserBlogDto dto) {
		String result = userBlogService.addUserBlog(token, dto);
		
		if(result.equals("200")) {
			return responseService.getSuccessResult();
		} else {
			return responseService.getFailResult(result);
		}
	}
	
	@ApiOperation(value = "블로그 수정", notes = "블로그 수정")
	@PutMapping("/blog/{uuid}")
	public CommonResult modifyUserBlog(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@PathVariable String uuid,
			@RequestBody UserBlogDto dto) {
		userBlogService.modifyUserBlog(token, uuid, dto);
		return null;
	}
	
	@ApiOperation(value = "블로그 삭제", notes = "블로그 삭제")
	@DeleteMapping("/blog/{uuid}")
	public CommonResult deleteUser(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		userBlogService.deleteUserBlog(token, uuid);
		return null;
	}
	
	
}
