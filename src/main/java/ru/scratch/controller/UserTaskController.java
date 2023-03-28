package ru.scratch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scratch.dto.TaskDTO;
import ru.scratch.dto.UserDTO;
import ru.scratch.dto.UserTaskDTO;
import ru.scratch.service.UserTaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userTask")
public class UserTaskController {

    private final UserTaskService userTaskService;

    @GetMapping
    public List<UserTaskDTO> getAll() {
        return userTaskService.getAll();
    }

    @GetMapping("/getAllByUserToken")
    public List<UserTaskDTO> getAllByUserToken() {
        return userTaskService.getAllByUserToken();
    }

    @PostMapping("/getAllByUser")
    public List<UserTaskDTO> getAllByUser(@RequestBody UserDTO userDTO) {
        return userTaskService.getAllByUser(userDTO);
    }

    @GetMapping("/getById/{id}")
    public UserTaskDTO getById(@PathVariable Long id) {
        return userTaskService.getById(id);
    }

    @PostMapping("/getByUserAndTask")
    public UserTaskDTO getById(@RequestBody TaskDTO taskDTO) {
        return userTaskService.getByUserAndTask(taskDTO);
    }

    @GetMapping("/getOrCreateByUserAndTaskId/{id}")
    public UserTaskDTO getOrCreateByUserAndTaskId(@PathVariable Long id) {
        return userTaskService.getOrCreateByUserAndTaskId(id);
    }
    @GetMapping("/getAllByGroupId/{id}")
    public List<UserTaskDTO> getAllByGroupId(@PathVariable Long id) {
        return userTaskService.getAllByGroupId(id);
    }

    @GetMapping("/getByUserIdAndTaskId/{userId}/{taskId}")
    public UserTaskDTO getByUserIdAndTaskId(@PathVariable Long userId, @PathVariable Long taskId) {
        return userTaskService.getByUserIdAndTaskId(userId, taskId);
    }

    @GetMapping("/exist/{userId}/{taskId}")
    public boolean exist(@PathVariable Long userId, @PathVariable Long taskId) {
        return userTaskService.exist(userId, taskId);
    }

    @PostMapping("/create")
    public UserTaskDTO create(@RequestBody UserTaskDTO userTaskDTO) {
        return userTaskService.create(userTaskDTO);
    }

    @PostMapping("/update")
    public UserTaskDTO update(@RequestBody UserTaskDTO userTaskDTO) {
        return userTaskService.update(userTaskDTO);
    }

    @PostMapping("/updateByStudent")
    public UserTaskDTO updateByStudent(@RequestBody UserTaskDTO userTaskDTO) {
        return userTaskService.updateByStudent(userTaskDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return userTaskService.deleteById(id);
    }
}
