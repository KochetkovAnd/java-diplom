package ru.scratch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scratch.domain.Command;
import ru.scratch.dto.CommandDTO;

import java.util.List;

@Mapper
public interface CommandMapper {

    CommandMapper COMMAND_MAPPER = Mappers.getMapper(CommandMapper.class);

    Command toEntity(CommandDTO commandDTO);
    CommandDTO toDTO(Command command);
    List<Command> allToEntity(List<CommandDTO> commandDTOList);
    List<CommandDTO> allToDTO(List<Command> commandList);
}
