package com.sfl.cafemanager.mapper;

import com.sfl.cafemanager.entity.UserEntity;
import com.sfl.cafemanager.rest.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);
}
