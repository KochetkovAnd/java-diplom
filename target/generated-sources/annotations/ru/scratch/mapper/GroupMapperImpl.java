package ru.scratch.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.scratch.domain.Group;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.dto.GroupDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:45:31+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Group toEntity(GroupDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( groupDTO.getId() );
        group.setName( groupDTO.getName() );
        group.setTeacher( groupDTO.getTeacher() );
        List<Task> list = groupDTO.getTasks();
        if ( list != null ) {
            group.setTasks( new ArrayList<Task>( list ) );
        }
        List<User> list1 = groupDTO.getStudents();
        if ( list1 != null ) {
            group.setStudents( new ArrayList<User>( list1 ) );
        }

        return group;
    }

    @Override
    public GroupDTO toDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setId( group.getId() );
        groupDTO.setName( group.getName() );
        groupDTO.setTeacher( group.getTeacher() );
        List<Task> list = group.getTasks();
        if ( list != null ) {
            groupDTO.setTasks( new ArrayList<Task>( list ) );
        }
        List<User> list1 = group.getStudents();
        if ( list1 != null ) {
            groupDTO.setStudents( new ArrayList<User>( list1 ) );
        }

        return groupDTO;
    }

    @Override
    public List<Group> allToEntity(List<GroupDTO> groupDTOList) {
        if ( groupDTOList == null ) {
            return null;
        }

        List<Group> list = new ArrayList<Group>( groupDTOList.size() );
        for ( GroupDTO groupDTO : groupDTOList ) {
            list.add( toEntity( groupDTO ) );
        }

        return list;
    }

    @Override
    public List<GroupDTO> allToDTO(List<Group> groupList) {
        if ( groupList == null ) {
            return null;
        }

        List<GroupDTO> list = new ArrayList<GroupDTO>( groupList.size() );
        for ( Group group : groupList ) {
            list.add( toDTO( group ) );
        }

        return list;
    }
}
