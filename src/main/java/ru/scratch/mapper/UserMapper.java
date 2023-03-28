package ru.scratch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scratch.domain.User;
import ru.scratch.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    List<User> allToEntity(List<UserDTO> userDTOList);
    List<UserDTO> allToDTO(List<User> userList);
}
