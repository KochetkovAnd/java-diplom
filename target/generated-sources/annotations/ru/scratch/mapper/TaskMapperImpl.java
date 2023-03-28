package ru.scratch.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.scratch.domain.Command;
import ru.scratch.domain.Task;
import ru.scratch.dto.TaskDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:45:31+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setOwner( taskDTO.getOwner() );
        task.setN( taskDTO.getN() );
        task.setM( taskDTO.getM() );
        task.setGrid( taskDTO.getGrid() );
        task.setX( taskDTO.getX() );
        task.setY( taskDTO.getY() );
        task.setAngle( taskDTO.getAngle() );
        task.setName( taskDTO.getName() );
        List<Command> list = taskDTO.getCommands();
        if ( list != null ) {
            task.setCommands( new ArrayList<Command>( list ) );
        }

        return task;
    }

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setOwner( task.getOwner() );
        taskDTO.setN( task.getN() );
        taskDTO.setM( task.getM() );
        taskDTO.setGrid( task.getGrid() );
        taskDTO.setX( task.getX() );
        taskDTO.setY( task.getY() );
        taskDTO.setAngle( task.getAngle() );
        taskDTO.setName( task.getName() );
        List<Command> list = task.getCommands();
        if ( list != null ) {
            taskDTO.setCommands( new ArrayList<Command>( list ) );
        }

        return taskDTO;
    }

    @Override
    public List<Task> allToEntity(List<TaskDTO> taskDTOList) {
        if ( taskDTOList == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( taskDTOList.size() );
        for ( TaskDTO taskDTO : taskDTOList ) {
            list.add( toEntity( taskDTO ) );
        }

        return list;
    }

    @Override
    public List<TaskDTO> allToDTO(List<Task> taskList) {
        if ( taskList == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( taskList.size() );
        for ( Task task : taskList ) {
            list.add( toDTO( task ) );
        }

        return list;
    }
}
