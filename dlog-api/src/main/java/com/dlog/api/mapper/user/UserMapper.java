package com.dlog.api.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dlog.api.dto.UserDto;
import com.dlog.api.model.user.User;

@Mapper(componentModel = "spring", uses = {},
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper  {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User toEntity(@MappingTarget User user, UserDto userDTO);
	
	UserDto toDto(@MappingTarget UserDto usetDto, User user);
}
