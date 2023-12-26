package com.dlog.api.service.blog;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("S3Service")
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
    	
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
			amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
		} catch (SdkClientException | IOException e) {
			System.out.println(e.getMessage());
		}
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
}
