package ru.scratch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scratch.domain.Task;
import ru.scratch.dto.TaskDTO;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);

    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
    List<Task> allToEntity(List<TaskDTO> taskDTOList);
    List<TaskDTO> allToDTO(List<Task> taskList);
}
