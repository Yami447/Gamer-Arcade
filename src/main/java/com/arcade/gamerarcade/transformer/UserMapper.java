package com.arcade.gamerarcade.transformer;


import com.arcade.beans.User;
import com.arcade.dto.UserCreateDto;
import com.arcade.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User fromUserCreateDto(UserCreateDto userCreateDto);
    UserResponseDto fromUser(User user);
}
