package com.dlog.api.model.response;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResult<T> extends CommonResult {
	
	private Page<T> page;
}
