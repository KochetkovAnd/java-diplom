package ru.scratch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.scratch.domain.Group;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.domain.UserTask;
import ru.scratch.dto.TaskDTO;
import ru.scratch.exception.ResourceNotFoundException;
import ru.scratch.mapper.TaskMapper;
import ru.scratch.repository.GroupRepository;
import ru.scratch.repository.TaskRepository;
import ru.scratch.repository.UserRepository;
import ru.scratch.repository.UserTaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserTaskRepository userTaskRepository;

    public List<TaskDTO> getAll() {
        return allToDTO(taskRepository.findAll());
    }

    public TaskDTO getById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return toDTO(task.get());
        } else {
            throw new ResourceNotFoundException("Task with id = " + id + " not found" ,"");
        }
    }

    public List<TaskDTO> getAvailableTasks() {
        User user = getUserByToken();
        Optional<Group> group = groupRepository.findById(user.getGroup().getId());
        if (group.isPresent()) {
            return allToDTO(group.get().getTasks());
        }
        throw new ResourceNotFoundException("User not linked with group", "");

    }

    public List<TaskDTO> getAllByUser() {
        User user = getUserByToken();
        return allToDTO(taskRepository.findAllByOwner(user));
    }



    public TaskDTO create(TaskDTO taskDTO) {
        taskRepository.save(toEntity(taskDTO));
        return taskDTO;
    }

    public TaskDTO update(TaskDTO taskDTO) {
        taskDTO.setOwner(getUserByToken());
        Optional<Task> optionalTask = taskRepository.findByName(taskDTO.getName());
        if (optionalTask.isPresent() && optionalTask.get().getId() != taskDTO.getId()) {
            throw new ResourceNotFoundException("Task with name = " + taskDTO.getName() + " already exist", "");
        }
        taskRepository.save(toEntity(taskDTO));
        return toDTO(taskRepository.findByName(taskDTO.getName()).get());
    }

    public boolean deleteById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return false;
        }
        taskRepository.delete(task.get());
        return true;
    }



    public User getUserByToken() {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(securityUser.getUsername()).get();
    }

    private Task toEntity(TaskDTO taskDTO) {
        return TaskMapper.TASK_MAPPER.toEntity(taskDTO);
    }
    private TaskDTO toDTO(Task task) {
        return TaskMapper.TASK_MAPPER.toDTO(task);
    }
    private List<Task> allToEntity(List<TaskDTO> taskDTOList) {
        return TaskMapper.TASK_MAPPER.allToEntity(taskDTOList);
    }
    private List<TaskDTO> allToDTO(List<Task> taskList) {
        return TaskMapper.TASK_MAPPER.allToDTO(taskList);
    }
}
