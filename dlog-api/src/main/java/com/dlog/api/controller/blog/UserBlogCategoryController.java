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

import com.dlog.api.dto.UserBlogCategoryDto;
import com.dlog.api.model.blog.UserBlogCategory;
import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.SingleResult;
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
	private final UserBlogCategoryRepository userBlogCategoryRepository;	

	@ApiOperation(value = "블로그 카테고리 specification 조회", notes = "블로그 카테고리 specification 조회")
	@GetMapping("/blog/categories")
	public ListResult<UserBlogCategory> getUserBlogs(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@RequestParam(required = false, defaultValue = "0") long rowNumber,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String userBlogId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue ="0") long depth,
            @RequestParam(required = false) String parentId,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) Boolean isDeleted ) {
		
		Map<String, Object> searchKeyword = new HashMap<>();
		
		if (rowNumber != 0) {
			searchKeyword.put("rowNumber", rowNumber);
        }
        if (uuid != null) {
			searchKeyword.put("uuid", uuid);
        }
        if (userBlogId != null) {
			searchKeyword.put("userBlogId", userBlogId);
        }
        if (name != null) {
			searchKeyword.put("name", name);
        }
        if (description != null) {
			searchKeyword.put("description", description);
        }
        if (depth > 0) {
			searchKeyword.put("depth", depth);
        }
        if (parentId != null) {
			searchKeyword.put("parentId", parentId);
        }
        if (createdBy != null) {
			searchKeyword.put("createdBy", createdBy);
        }
        if (isDeleted != null) {
			searchKeyword.put("isDeleted", isDeleted);
        }
        
        return userBlogCategoryService.getUserBlogCategories(token, searchKeyword);
        
	}
	
	@ApiOperation(value = "블로그 카테고리 단일 조회", notes = "블로그 카테고리 단일 조회")
	@GetMapping("/blog/category/{uuid}")
	public SingleResult<UserBlogCategory> getUserByUuid(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		return responseService.getSingleResult(userBlogCategoryRepository.findByUuid(uuid).orElse(null));
	}
	
	@ApiOperation(value = "블로그 생성", notes = "블로그 생성")
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
	public CommonResult modifyUserBlog(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@PathVariable String uuid,
			@RequestBody UserBlogCategoryDto dto) {
		userBlogCategoryService.modifyUserBlogCategory(token, uuid, dto);
		return null;
	}
	
	@ApiOperation(value = "블로그 삭제", notes = "블로그 삭제")
	@DeleteMapping("/blog/category/{uuid}")
	public CommonResult deleteUser(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		userBlogCategoryService.deleteUserBlogCategory(token, uuid);
		return null;
	}
	
	
}
