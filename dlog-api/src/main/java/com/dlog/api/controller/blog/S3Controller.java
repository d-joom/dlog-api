package com.dlog.api.controller.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dlog.api.service.blog.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"s3-controller"})
@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties
public class S3Controller {
	
	@Value("${cloud.aws.s3.bucket}")
    private String bucket;
	
    @Value("${cloud.aws.access-key}")
    private String accessKey;

    @Value("${cloud.aws.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;
	
	private final S3Service s3Service;
	
	@ApiOperation(value = "s3에 이미지 업로드", notes = "s3에 이미지 업로드")
	@PostMapping("/s3/upload")
	public String uploadImage(@RequestHeader(required = true, defaultValue = "Bearer TOKEN_VALUE") String token,
			@RequestParam("multipartFile") MultipartFile file) {

		String result = s3Service.uploadImage(file);
		
		return result;
	}
	
	
}
