package ru.scratch.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.scratch.domain.Command;
import ru.scratch.dto.CommandDTO;
import ru.scratch.dto.GroupDTO;
import ru.scratch.mapper.CommandMapper;
import ru.scratch.repository.CommandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository commandRepository;

    public List<CommandDTO> getAll() {
        return allToDTO(commandRepository.findAll());
    }








    private Command toEntity(CommandDTO commandDTO) {
        return CommandMapper.COMMAND_MAPPER.toEntity(commandDTO);
    }
    private CommandDTO toDTO(Command command) {
        return CommandMapper.COMMAND_MAPPER.toDTO(command);
    }
    private List<Command> allToEntity(List<CommandDTO> commandDTOList) {
        return CommandMapper.COMMAND_MAPPER.allToEntity(commandDTOList);
    }
    private List<CommandDTO> allToDTO(List<Command> commandList) {
        return CommandMapper.COMMAND_MAPPER.allToDTO(commandList);
    }
}
