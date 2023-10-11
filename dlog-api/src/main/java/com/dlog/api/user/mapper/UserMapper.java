package com.dlog.api.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.dlog.api.user.Dto.UserDto;
import com.dlog.api.user.model.User;

@Mapper(componentModel = "spring", uses = {},
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.RETURN_DEFAULT,
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper  {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User toEntity(@MappingTarget User user, UserDto userDTO);
	
	UserDto toDto(@MappingTarget UserDto usetDto, User user);
}
