package com.dlog.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dlog.api.model.response.CommonResult;
import com.dlog.api.model.response.ListResult;
import com.dlog.api.model.response.PageResult;
import com.dlog.api.model.response.SingleResult;

@Service
public class ResponseService {

	public enum CommonResponse {
		SUCCESS(0, "성공하였습니다."),
		FAIL(-1, "실패하였습니다");
		
		int code;
		String message;
		
		CommonResponse(int code, String message){
			this.code = code;
			this.message = message;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}
	
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}
	
    public <T> PageResult<T> getPageResult(Page<T> pageList) {
        PageResult<T> result = new PageResult<>();
        result.setPage(pageList);
        setSuccessResult(result);
        return result;
    }
	
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}
	
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMessage(CommonResponse.FAIL.getMessage());
		return result;
	}
	
	public CommonResult getFailResult(String message) {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMessage(message);
		return result;
	}
	
	public void setSuccessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMessage(CommonResponse.SUCCESS.getMessage());
	}
}
