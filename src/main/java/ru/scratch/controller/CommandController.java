package ru.scratch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scratch.dto.CommandDTO;
import ru.scratch.dto.GroupDTO;
import ru.scratch.service.CommandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/command")
public class CommandController {

    private final CommandService commandService;

    @GetMapping
    public List<CommandDTO> getAll() {
        return commandService.getAll();
    }

}
