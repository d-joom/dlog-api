package com.dlog.api.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.SingleResult;
import com.dlog.api.service.ResponseService;
import com.dlog.api.user.Dto.CreateUserDto;
import com.dlog.api.user.model.User;
import com.dlog.api.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Api(tags = {"users-controller"})
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties
public class UserController {
	
	private final UserService userService;
	private final ResponseService responseService;
	
	@Value("${spring.datasource.username}")
	private static String DATASOURCE_USERNAME;

	@ApiOperation(value = "회원 specification 조회", notes = "회원 specification 조회")
	@GetMapping("/users")
	public ListResult<User> getUsers(@RequestParam(required = false, defaultValue = "0") long rowId,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nickName,
            @RequestParam(required = false) String picture,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean isDeleted ) {
		
		Map<String, Object> searchKeyword = new HashMap<>();
		
		if (rowId != 0) {
			searchKeyword.put("rowId", rowId);
        }
        if (id != null) {
			searchKeyword.put("id", id);
        }
        if (password != null) {
			searchKeyword.put("password", password);
        }
        if (name != null) {
			searchKeyword.put("name", name);
        }
        if (nickName != null) {
			searchKeyword.put("nickName", nickName);
        }
        if (picture != null) {
			searchKeyword.put("picture", picture);
        }
        if (email != null) {
			searchKeyword.put("email", email);
        }
        if (mobile != null) {
			searchKeyword.put("mobile", mobile);
        }
        if (gender != null) {
			searchKeyword.put("gender", gender);
        }
        if (isDeleted != null) {
			searchKeyword.put("isDeleted", isDeleted);
        }
        
        List<User> users = userService.getUsers(searchKeyword);
        
        System.out.println();
        return responseService.getListResult(users);
	}
	
	@ApiOperation(value = "회원 단일 조회", notes = "회원 단일 조회")
	@GetMapping("/user/{uuid}")
	public SingleResult<User> getUserByUuid(@PathVariable String uuid) {
		return null;
	}
	
	@ApiOperation(value = "회원 생성", notes = "회원 생성")
	@PostMapping("/user")
	public CommonResult addUser(@RequestBody CreateUserDto dto) {
		String result = userService.addUser(dto);
		
		if(result.equals("200")) {
			return responseService.getSuccessResult();
		} else {
			return responseService.getFailResult(result);
		}
	}
	
	@ApiOperation(value = "회원 수정", notes = "회원 수정")
	@PutMapping("/user")
	public CommonResult modifyUser() {
		return null;
	}
	
	@ApiOperation(value = "회원 삭제", notes = "회원 삭제")
	@DeleteMapping("/user/{uuid}")
	public CommonResult deleteUser() {
		return null;
	}
	
}
