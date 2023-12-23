package com.dlog.api.controller.blog;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dlog.api.dto.UserBlogCategoryDto;
import com.dlog.api.dto.UserBlogCategoryResult;
import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.repository.blog.UserBlogCategoryRepository;
import com.dlog.api.service.ResponseService;
import com.dlog.api.service.blog.UserBlogCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Api(tags = {"user-blog-categories-controller"})
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UserBlogCategoryController {
	
	private final UserBlogCategoryService userBlogCategoryService;
	private final ResponseService responseService;
	
	@ApiOperation(value = "블로그 카테고리 전체 조회", notes = "블로그 카테고리 전체 조회")
	@GetMapping("/blog/categories/all")
	public ListResult<UserBlogCategoryResult> getAllUserBlogCategories(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token) {
		return responseService.getListResult(userBlogCategoryService.getUserBlogCategories());
	}
	
	@ApiOperation(value = "블로그 카테고리 전체 조회 (user_blog_id 기준)", notes = "블로그 카테고리 전체 조회 (user_blog_id 기준)")
	@GetMapping("/blog/categories/{userBlogId}")
	public ListResult<UserBlogCategoryResult> getUserBlogCategoryByUserBlogId(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String userBlogId) {
		return responseService.getListResult(userBlogCategoryService.getUserBlogCategoryByUserBlogId(userBlogId));
	}
	
	
	@ApiOperation(value = "블로그 카테고리 생성", notes = "블로그 카테고리 생성")
	@PostMapping("/blog/category")
	public CommonResult addUserBlog(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@RequestBody UserBlogCategoryDto dto) {
		String result = userBlogCategoryService.addUserBlogCategory(token, dto);
		
		if(result.equals("200")) {
			return responseService.getSuccessResult();
		} else {
			return responseService.getFailResult(result);
		}
	}
	
	@ApiOperation(value = "블로그 카테고리 수정", notes = "블로그 카테고리 수정")
	@PutMapping("/blog/category/{uuid}")
	public CommonResult modifyUserBlogCategory(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@PathVariable String uuid,
			@RequestBody UserBlogCategoryDto dto) {
		userBlogCategoryService.modifyUserBlogCategory(token, uuid, dto);
		return null;
	}
	
	@ApiOperation(value = "블로그 카테고리 삭제", notes = "블로그 카테고리 삭제")
	@DeleteMapping("/blog/category/{uuid}")
	public CommonResult deleteUserBlogCategory(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		userBlogCategoryService.deleteUserBlogCategory(token, uuid);
		return null;
	}
	
	
}
