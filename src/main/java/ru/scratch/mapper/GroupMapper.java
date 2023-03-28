package ru.scratch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scratch.domain.Group;
import ru.scratch.dto.GroupDTO;

import java.util.List;

@Mapper
public interface GroupMapper {

    GroupMapper GROUP_MAPPER = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupDTO groupDTO);
    GroupDTO toDTO(Group group);
    List<Group> allToEntity(List<GroupDTO> groupDTOList);
    List<GroupDTO> allToDTO(List<Group> groupList);
}
