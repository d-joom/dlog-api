package com.dlog.api.service.blog;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	public String uploadImage(MultipartFile file);
	
	
}
