package com.dlog.api.service.blog;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dlog.api.dto.UserBlogCategoryDto;
import com.dlog.api.dto.UserBlogCategoryResult;
import com.dlog.api.dto.UserBlogTopCategoryDto;
import com.dlog.api.dto.UserInfoDto;
import com.dlog.api.model.blog.UserBlog;
import com.dlog.api.model.blog.UserBlogCategory;
import com.dlog.api.repository.blog.UserBlogCategoryQuerydslRepository;
import com.dlog.api.repository.blog.UserBlogCategoryRepository;
import com.dlog.api.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userBlogCategoryService")
public class UserBlogCategoryServiceImpl implements UserBlogCategoryService {

	private final UserBlogCategoryRepository userBlogCategoryRepository;
	
	private final UserBlogCategoryQuerydslRepository userBlogCategoryQuerydslRepository;

	private final ModelMapper modelMapper;


	@Override
	public List<UserBlogCategoryResult> getUserBlogCategories() {
		final List<UserBlogCategory> all = userBlogCategoryQuerydslRepository.findAllWithQuerydsl();
		return all.stream().map(UserBlogCategoryResult::new).collect(Collectors.toList());
	}
	
	@Override
	public List<UserBlogCategoryResult> getUserBlogCategoryByUserBlogId(String userBlogId) {
		final List<UserBlogCategory> all = userBlogCategoryQuerydslRepository.findAllWithQuerydslByUserBlogId(userBlogId);
		return all.stream().map(UserBlogCategoryResult::new).collect(Collectors.toList());
	}
	
	public List<UserBlogTopCategoryDto> getUserBlogTopCategoryByUserBlogId(String userBlogId) {
		
		Pageable pageable = PageRequest.of(0, 5); // 0은 페이지 번호, 5는 페이지 크기

		List<UserBlogTopCategoryDto> result = userBlogCategoryRepository.getBlogCategoryWithPostCount(userBlogId, pageable);
		return result;
	}

	@Override
	public String addUserBlogCategory(String token, UserBlogCategoryDto dto) {

		try {
			 final UserBlogCategory parent = userBlogCategoryRepository.findByRowId(dto.getParentId())
		                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Menu 입니다."));
			 
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));

			UserBlogCategory userBlogCategory = new UserBlogCategory();
			userBlogCategory.setUuid(UUID.randomUUID().toString());
			userBlogCategory.setUserBlogId(dto.getUserBlogId());
			userBlogCategory.setName(dto.getName());
			userBlogCategory.setDescription(dto.getDescription());
			userBlogCategory.setDepth(dto.getDepth());
			userBlogCategory.setCreatedBy(userInfo.getEmail());

			if (parent != null) {
				userBlogCategory.updateParent(parent);
			}

			userBlogCategoryRepository.save(userBlogCategory);

			return "200";
		} catch (Exception e) {
			e.getStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String modifyUserBlogCategory(String token, String uuid, UserBlogCategoryDto dto) {

		try {
			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));

			UserBlogCategory userBlog = userBlogCategoryRepository.findByUuid(uuid).orElse(null);

			if (userBlog == null) {
				return "유효하지 않은 user blog rowNumber 입니다.";
			} else {

				if (!userBlog.getRowId().equals(userInfo.getRowId())) {
					return "수정할 권한이 없습니다.";
				}

				UserBlog result = modelMapper.map(dto, UserBlog.class);
				modelMapper.map(result, userBlog);

				userBlogCategoryRepository.save(userBlog);
			}
		} catch (Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return null;
	}

	@Override
	public String deleteUserBlogCategory(String token, String uuid) {

		try {

			UserInfoDto userInfo = JwtTokenUtil.getUserInfo(token.replace("Bearer ", ""));

			UserBlogCategory userBlogCategory = userBlogCategoryRepository.findByUuid(uuid).orElse(null);

			if (userBlogCategory == null) {
				return "유효하지 않은 user blog rowId 입니다.";
			} else {
				if (!userBlogCategory.getRowId().equals(userInfo.getRowId())) {
					return "삭제할 권한이 없습니다.";
				}
				userBlogCategory.setIsDeleted(true);
				userBlogCategoryRepository.save(userBlogCategory);
			}
		} catch (Exception e) {
			System.out.println("ERROR --- " + e.getMessage());
		}

		return "200";
	}
}
