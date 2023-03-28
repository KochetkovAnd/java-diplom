package ru.scratch.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.scratch.domain.UserTask;
import ru.scratch.dto.UserTaskDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:45:31+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class UserTaskMapperImpl implements UserTaskMapper {

    @Override
    public UserTask toEntity(UserTaskDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        UserTask userTask = new UserTask();

        userTask.setId( groupDTO.getId() );
        userTask.setUser( groupDTO.getUser() );
        userTask.setTask( groupDTO.getTask() );
        userTask.setSolved( groupDTO.getSolved() );
        userTask.setSolution( groupDTO.getSolution() );
        userTask.setMark( groupDTO.getMark() );

        return userTask;
    }

    @Override
    public UserTaskDTO toDTO(UserTask group) {
        if ( group == null ) {
            return null;
        }

        UserTaskDTO userTaskDTO = new UserTaskDTO();

        userTaskDTO.setId( group.getId() );
        userTaskDTO.setUser( group.getUser() );
        userTaskDTO.setTask( group.getTask() );
        userTaskDTO.setSolved( group.getSolved() );
        userTaskDTO.setSolution( group.getSolution() );
        userTaskDTO.setMark( group.getMark() );

        return userTaskDTO;
    }

    @Override
    public List<UserTask> allToEntity(List<UserTaskDTO> groupDTOList) {
        if ( groupDTOList == null ) {
            return null;
        }

        List<UserTask> list = new ArrayList<UserTask>( groupDTOList.size() );
        for ( UserTaskDTO userTaskDTO : groupDTOList ) {
            list.add( toEntity( userTaskDTO ) );
        }

        return list;
    }

    @Override
    public List<UserTaskDTO> allToDTO(List<UserTask> userTaskList) {
        if ( userTaskList == null ) {
            return null;
        }

        List<UserTaskDTO> list = new ArrayList<UserTaskDTO>( userTaskList.size() );
        for ( UserTask userTask : userTaskList ) {
            list.add( toDTO( userTask ) );
        }

        return list;
    }
}
