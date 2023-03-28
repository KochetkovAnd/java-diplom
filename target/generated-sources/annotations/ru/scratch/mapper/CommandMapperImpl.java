package ru.scratch.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.scratch.domain.Command;
import ru.scratch.dto.CommandDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-28T22:45:31+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"
)
public class CommandMapperImpl implements CommandMapper {

    @Override
    public Command toEntity(CommandDTO commandDTO) {
        if ( commandDTO == null ) {
            return null;
        }

        Command command = new Command();

        command.setId( commandDTO.getId() );
        command.setType( commandDTO.getType() );
        command.setText( commandDTO.getText() );
        command.setColor( commandDTO.getColor() );
        command.setNumberOfRepeats( commandDTO.getNumberOfRepeats() );

        return command;
    }

    @Override
    public CommandDTO toDTO(Command command) {
        if ( command == null ) {
            return null;
        }

        CommandDTO commandDTO = new CommandDTO();

        commandDTO.setId( command.getId() );
        commandDTO.setType( command.getType() );
        commandDTO.setText( command.getText() );
        commandDTO.setColor( command.getColor() );
        commandDTO.setNumberOfRepeats( command.getNumberOfRepeats() );

        return commandDTO;
    }

    @Override
    public List<Command> allToEntity(List<CommandDTO> commandDTOList) {
        if ( commandDTOList == null ) {
            return null;
        }

        List<Command> list = new ArrayList<Command>( commandDTOList.size() );
        for ( CommandDTO commandDTO : commandDTOList ) {
            list.add( toEntity( commandDTO ) );
        }

        return list;
    }

    @Override
    public List<CommandDTO> allToDTO(List<Command> commandList) {
        if ( commandList == null ) {
            return null;
        }

        List<CommandDTO> list = new ArrayList<CommandDTO>( commandList.size() );
        for ( Command command : commandList ) {
            list.add( toDTO( command ) );
        }

        return list;
    }
}
