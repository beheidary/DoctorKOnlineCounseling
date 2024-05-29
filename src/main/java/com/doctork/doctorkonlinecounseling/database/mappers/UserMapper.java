package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", ignore = true)
    UserEntity userToUserEntity(User user);

    @Mapping(target = "authorities", ignore = true)
    User userEntityToUser(UserEntity userEntity);

    default List<GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return new ArrayList<>(authorities);
    }

    @AfterMapping
    default void afterUserToUserEntity(User user, @MappingTarget UserEntity userEntity) {
        userEntity.setAuthorities(mapAuthorities(user.getAuthorities()));
    }

    @AfterMapping
    default void afterUserEntityToUser(UserEntity userEntity, @MappingTarget User user) {
        user.setAuthorities(mapAuthorities(userEntity.getAuthorities()));
    }
}