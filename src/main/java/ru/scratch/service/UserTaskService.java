package ru.scratch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.scratch.domain.Group;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.domain.UserTask;
import ru.scratch.domain.keys.UserTaskKey;
import ru.scratch.dto.TaskDTO;
import ru.scratch.dto.UserDTO;
import ru.scratch.dto.UserTaskDTO;
import ru.scratch.exception.ResourceNotFoundException;
import ru.scratch.mapper.TaskMapper;
import ru.scratch.mapper.UserMapper;
import ru.scratch.mapper.UserTaskMapper;
import ru.scratch.repository.GroupRepository;
import ru.scratch.repository.TaskRepository;
import ru.scratch.repository.UserRepository;
import ru.scratch.repository.UserTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;

    public List<UserTaskDTO> getAll() {
        return allToDTO(userTaskRepository.findAll());
    }

    public UserTaskDTO getById(Long id) {
        Optional<UserTask> userTask = userTaskRepository.findById(id);
        if (userTask.isPresent()) {
            return toDTO(userTask.get());
        } else {
            throw new ResourceNotFoundException("UserTask with id = " + id + " not found" ,"");
        }
    }

    public List<UserTaskDTO> getAllByUserToken() {
        User user = getUserByToken();
        return allToDTO(userTaskRepository.findAllByUser(user));
    }

    public List<UserTaskDTO> getAllByUser(UserDTO userDTO) {
        User user = UserMapper.USER_MAPPER.toEntity(userDTO);
        return allToDTO(userTaskRepository.findAllByUser(user));
    }

    public UserTaskDTO getOrCreateByUserAndTaskId(Long taskId) {
        User user = getUserByToken();
        Task task = taskRepository.findById(taskId).get();
        Optional<UserTask> optionalUserTask = userTaskRepository.findByUserAndTask(user, task);
        if (!optionalUserTask.isPresent()) {
            UserTaskDTO userTaskDTO = new UserTaskDTO();
            userTaskDTO.setUser(user);
            userTaskDTO.setTask(task);
            userTaskDTO.setSolved(false);
            userTaskDTO.setSolution("");
            userTaskDTO.setId(new UserTaskKey(userTaskDTO.getUser().getId(), userTaskDTO.getTask().getId()));
            return toDTO(userTaskRepository.save(toEntity(userTaskDTO)));
        }
        return toDTO(optionalUserTask.get());
    }


    public UserTaskDTO getByUserIdAndTaskId(Long userId, Long taskId) {
        Optional<UserTask> userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId);
        if (userTask.isPresent()) {
            return toDTO(userTask.get());
        } else {
            throw new ResourceNotFoundException("UserTask with userId = " + userId + " and taskId = "+ taskId +" not found" ,"");
        }
    }

    public boolean exist(Long userId, Long taskId) {
        List<Group> groups = groupRepository.findAllByTeacher(getUserByToken());
        for (int i = 0; i < groups.size(); i++) {
            User user = userRepository.getById(userId);
            if (groups.get(i).getStudents().contains(user)) {
                try {
                    getByUserIdAndTaskId(userId, taskId);
                    return true;
                } catch (ResourceNotFoundException err) {
                    return false;
                }
            }
        }
        return false;
    }

    public UserTaskDTO getByUserAndTask(TaskDTO taskDTO) {
        User user = getUserByToken();
        Task task = TaskMapper.TASK_MAPPER.toEntity(taskDTO);
        Optional<UserTask> userTask = userTaskRepository.findByUserAndTask(user, task);
        if (userTask.isPresent()) {
            return toDTO(userTask.get());
        } else {
            throw new ResourceNotFoundException("the user is not connected to the task with id = " + task.getId(),"");
        }
    }

    public List<UserTaskDTO> getAllByGroupId(Long id) {
        return allToDTO(userTaskRepository.findAllByUserGroupId(id));
    }

    public UserTaskDTO create(UserTaskDTO userTaskDTO) {
        userTaskRepository.save(toEntity(userTaskDTO));
        return userTaskDTO;
    }

    public UserTaskDTO update(UserTaskDTO userTaskDTO) {
        userTaskDTO.setId(new UserTaskKey(userTaskDTO.getUser().getId(), userTaskDTO.getTask().getId()));
        return toDTO(userTaskRepository.save(toEntity(userTaskDTO)));
    }

    public UserTaskDTO updateByStudent(UserTaskDTO userTaskDTO) {
        userTaskDTO.setUser(getUserByToken());
        userTaskDTO.setId(new UserTaskKey(userTaskDTO.getUser().getId(), userTaskDTO.getTask().getId()));
        return toDTO(userTaskRepository.save(toEntity(userTaskDTO)));
    }

    public boolean deleteById(Long id) {
        Optional<UserTask> userTask = userTaskRepository.findById(id);
        if (!userTask.isPresent()) {
            return false;
        }
        userTaskRepository.delete(userTask.get());
        return true;
    }

    public User getUserByToken() {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(securityUser.getUsername()).get();
    }
    private UserTask toEntity(UserTaskDTO userTaskDTO) {
        return UserTaskMapper.USER_TASK_MAPPER.toEntity(userTaskDTO);
    }
    private UserTaskDTO toDTO(UserTask userTask) {
        return UserTaskMapper.USER_TASK_MAPPER.toDTO(userTask);
    }
    private List<UserTask> allToEntity(List<UserTaskDTO> userTaskDTOList) {
        return UserTaskMapper.USER_TASK_MAPPER.allToEntity(userTaskDTOList);
    }
    private List<UserTaskDTO> allToDTO(List<UserTask> userTaskList) {
        return UserTaskMapper.USER_TASK_MAPPER.allToDTO(userTaskList);
    }
}