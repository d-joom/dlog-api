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

import com.dlog.api.dto.UserPostDto;
import com.dlog.api.model.blog.UserPost;
import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.SingleResult;
import com.dlog.api.repository.blog.UserPostRepository;
import com.dlog.api.service.ResponseService;
import com.dlog.api.service.blog.UserPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Api(tags = {"user-posts-controller"})
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UserPostController {
	
	private final UserPostService userPostService;
	private final ResponseService responseService;
	private final UserPostRepository userPostRepository;	

	@ApiOperation(value = "포스트 specification 조회", notes = "포스트 specification 조회")
	@GetMapping("/posts")
	public ListResult<UserPost> getUserPosts(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@RequestParam(required = false, defaultValue = "0") long rowId,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String userBlogId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String contents,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) Boolean isTemporary,
            @RequestParam(required = false) Boolean isDeleted ) {
		
		Map<String, Object> searchKeyword = new HashMap<>();
		
		if (rowId != 0) {
			searchKeyword.put("rowId", rowId);
        }
        if (uuid != null) {
			searchKeyword.put("uuid", uuid);
        }
        if (userBlogId != null) {
			searchKeyword.put("userBlogId", userBlogId);
        }
        if (title != null) {
			searchKeyword.put("title", title);
        }
        if (contents != null) {
			searchKeyword.put("contents", contents);
        }
        if (createdBy != null) {
			searchKeyword.put("createdBy", createdBy);
        }
        if (isTemporary != null) {
			searchKeyword.put("isTemporary", isTemporary);
        }
        if (isDeleted != null) {
			searchKeyword.put("isDeleted", isDeleted);
        }
        
        return userPostService.getUserPosts(token, searchKeyword);
        
	}
	
	@ApiOperation(value = "포스트 단일 조회", notes = "포스트 단일 조회")
	@GetMapping("/post/{uuid}")
	public SingleResult<UserPost> getUserPostByUuid(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		return responseService.getSingleResult(userPostRepository.findByUuid(uuid).orElse(null));
	}
	
	@ApiOperation(value = "포스트 생성", notes = "포스트 생성")
	@PostMapping("/post")
	public CommonResult addUserPost(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@RequestBody UserPostDto dto) {
		String result = userPostService.addUserPost(token, dto);
		
		if(result.equals("200")) {
			return responseService.getSuccessResult();
		} else {
			return responseService.getFailResult(result);
		}
	}
	
	@ApiOperation(value = "블로그 수정", notes = "블로그 수정")
	@PutMapping("/post/{uuid}")
	public CommonResult modifyUserPost(
			@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token, 
			@PathVariable String uuid,
			@RequestBody UserPostDto dto) {
		System.out.println("title---" + dto.getTitle());
		System.out.println("contents-----" + dto.getContents());
		String result = userPostService.modifyUserPost(token, uuid, dto);
		if(result.equals("200")) {
			return responseService.getSuccessResult();
		} else {
			return responseService.getFailResult(result);
		}
	}
	
//	AKIAY2LPCNDDLCW6YMWY
	
//	UUEE9d3tLlawTd2QfdLoVGkWRb1d1pYkIkRK2qCE
	
	@ApiOperation(value = "블로그 삭제", notes = "블로그 삭제")
	@DeleteMapping("/post/{uuid}")
	public CommonResult deleteUserPost(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@PathVariable String uuid) {
		userPostService.deleteUserPost(token, uuid);
		return null;
	}
	
	
}
