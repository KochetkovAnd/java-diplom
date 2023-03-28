package ru.scratch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scratch.dto.TaskDTO;
import ru.scratch.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDTO> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/getById/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/getAvailableTasks")
    public List<TaskDTO> getAvailableTasks() {
        return taskService.getAvailableTasks();
    }

    @GetMapping("/getAllByUser")
    public List<TaskDTO> getAllByUser() {
        return taskService.getAllByUser();
    }

    @PostMapping("/create")
    public TaskDTO create(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PostMapping("/update")
    public TaskDTO update(@RequestBody TaskDTO taskDTO) {
        return taskService.update(taskDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }
}
