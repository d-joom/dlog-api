package com.dlog.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
	
	private String title;
	
	private String contents;
	
	private Boolean isTemporary;
	
}
