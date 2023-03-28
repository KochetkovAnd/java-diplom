package ru.scratch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scratch.dto.GroupDTO;
import ru.scratch.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/getById/{id}")
    public GroupDTO getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @GetMapping("/getAllByTeacher")
    public List<GroupDTO> getAllByTeacher() {
        return groupService.getAllByTeacher();
    }


    @PostMapping("/create")
    public GroupDTO create(@RequestBody GroupDTO groupDTO) {
        return groupService.create(groupDTO);
    }

    @PostMapping("/update")
    public GroupDTO update(@RequestBody GroupDTO groupDTO) {
        return groupService.update(groupDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return groupService.deleteById(id);
    }

    @DeleteMapping("/sureDeleteById/{id}")
    public boolean sureDeleteById(@PathVariable Long id) {
        return groupService.sureDeleteById(id);
    }

    @GetMapping("/canDeleteById/{id}")
    public boolean canDeleteById(@PathVariable Long id) {
        return groupService.canDeleteById(id);
    }


}
