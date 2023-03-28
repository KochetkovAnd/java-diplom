package ru.scratch.service;

import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scratch.domain.Group;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.domain.UserTask;
import ru.scratch.domain.enums.Role;
import ru.scratch.dto.UserDTO;
import ru.scratch.exception.ResourceNotFoundException;
import ru.scratch.mapper.UserMapper;
import ru.scratch.repository.GroupRepository;
import ru.scratch.repository.TaskRepository;
import ru.scratch.repository.UserRepository;
import ru.scratch.repository.UserTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;
    private  final UserTaskRepository userTaskRepository;

    public List<UserDTO> getAll() {
        return allToDTO(userRepository.findAll());
    }

    public UserDTO getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return toDTO(user.get());
        } else {
            throw new ResourceNotFoundException("User with id = " + id + " not found" ,"");
        }
    }

    public List<UserDTO> getAllByGroupId(Long id) {
        return allToDTO(userRepository.findAllByGroupId(id));
    }


    public List<UserDTO> getAllStudentsWithoutGroup() {
        return allToDTO(userRepository.findAllByGroupAndRole(null, Role.STUDENT));
    }

    public List<UserDTO> getAllTeachers() {
        return allToDTO(userRepository.findAllByRole( Role.TEACHER));
    }


    public UserDTO create(UserDTO userDTO) {
        userRepository.save(toEntity(userDTO));
        return userDTO;
    }

    public UserDTO update(UserDTO userDTO) {
        userRepository.save(toEntity(userDTO));
        return userDTO;
    }

    public boolean deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return false;
        }
        userRepository.delete(user.get());
        return true;
    }

    public boolean canDeleteById(Long id) {
        List<Group> groups = groupRepository.findAllByTeacherId(id);
        List<Task> tasks = taskRepository.findAllByOwnerId(id);
        List<UserTask> userTasks = userTaskRepository.findAllByUserId(id);
        return groups.isEmpty() && tasks.isEmpty() && userTasks.isEmpty();
    }
    @Transactional
    public boolean sureDeleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        List<Group> groupList = groupRepository.findAllByTeacher(user);
        groupList.forEach(group -> {
            group.setTeacher(null);
            groupRepository.save(group);
        });
        List<Task> taskList = taskRepository.findAllByOwner(user);
        taskList.forEach(task -> {
            task.setOwner(null);
            taskRepository.save(task);
        });
        userTaskRepository.deleteAllByUser(user);
        userRepository.delete(user);
        return true;
    }


    private User toEntity(UserDTO userDTO) {
        return UserMapper.USER_MAPPER.toEntity(userDTO);
    }
    private UserDTO toDTO(User user) {
        return UserMapper.USER_MAPPER.toDTO(user);
    }
    private List<User> allToEntity(List<UserDTO> userDTOList) {
        return UserMapper.USER_MAPPER.allToEntity(userDTOList);
    }
    private List<UserDTO> allToDTO(List<User> userList) {
        return UserMapper.USER_MAPPER.allToDTO(userList);
    }
}