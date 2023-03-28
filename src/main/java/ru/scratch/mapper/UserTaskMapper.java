package ru.scratch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scratch.domain.UserTask;
import ru.scratch.dto.UserTaskDTO;

import java.util.List;

@Mapper
public interface UserTaskMapper {

    UserTaskMapper USER_TASK_MAPPER = Mappers.getMapper(UserTaskMapper.class);

    UserTask toEntity(UserTaskDTO groupDTO);
    UserTaskDTO toDTO(UserTask group);
    List<UserTask> allToEntity(List<UserTaskDTO> groupDTOList);
    List<UserTaskDTO> allToDTO(List<UserTask> userTaskList);
}