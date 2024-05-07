package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity DtoToEntity(RegisterUserDto userDto);
    //ResponseEntity EntityToDto(RegisterUserDto userDto);

}
