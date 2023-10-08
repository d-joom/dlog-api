package com.dlog.api.user.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
	
	private String id;
	
	private String password;
	
	private String name;
	
	private String nickNmae;
	
	private String email;
	
	private String mobile;
	
	private String gender;
	
}
