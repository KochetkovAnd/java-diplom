package ru.scratch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.scratch.domain.Group;
import ru.scratch.domain.User;
import ru.scratch.dto.GroupDTO;
import ru.scratch.exception.ResourceNotFoundException;
import ru.scratch.mapper.GroupMapper;
import ru.scratch.repository.GroupRepository;
import ru.scratch.repository.UserRepository;
import ru.scratch.security.RegisterRequestDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public List<GroupDTO> getAll() {
        return allToDTO(groupRepository.findAll());
    }

    public GroupDTO getById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            return toDTO(group.get());
        } else {
            throw new ResourceNotFoundException("Group with id = " + id + " not found" ,"");
        }
    }

    public List<GroupDTO> getAllByTeacher() {
        return allToDTO(groupRepository.findAllByTeacher(getUserByToken()));
    }

    public GroupDTO create(GroupDTO groupDTO) {
        if (!groupRepository.findByName(groupDTO.getName()).isPresent()) {
            groupRepository.save(toEntity(groupDTO));
            return toDTO(groupRepository.findByName(groupDTO.getName()).get());
        } else  {
            throw new ResourceNotFoundException("Group with name = " + groupDTO.getName() + " already exist", "");
        }
    }

    public GroupDTO update(GroupDTO groupDTO) {
        Optional<Group> optionalGroup = groupRepository.findByName(groupDTO.getName());
        if (optionalGroup.isPresent() && optionalGroup.get().getId() != groupDTO.getId()) {
            throw new ResourceNotFoundException("Group with name = " + groupDTO.getName() + " already exist", "");
        }
        groupRepository.save(toEntity(groupDTO));
        return groupDTO;
    }

    public boolean deleteById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (!group.isPresent()) {
            return false;
        }
        groupRepository.delete(group.get());
        return true;
    }

    public boolean sureDeleteById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (!group.isPresent()) {
            return false;
        }
        List<User> userList = userRepository.findAllByGroupId(id);
        userList.forEach(user -> {
            user.setGroup(null);
            userRepository.save(user);
        });
        groupRepository.delete(group.get());
        return true;
    }

    public boolean canDeleteById(Long id) {
        List<User> users = userRepository.findAllByGroupId(id);
        return users.isEmpty();
    }

    public User getUserByToken() {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(securityUser.getUsername()).get();
    }

    private Group toEntity(GroupDTO groupDTO) {
        return GroupMapper.GROUP_MAPPER.toEntity(groupDTO);
    }
    private GroupDTO toDTO(Group group) {
        return GroupMapper.GROUP_MAPPER.toDTO(group);
    }
    private List<Group> allToEntity(List<GroupDTO> groupDTOList) {
        return GroupMapper.GROUP_MAPPER.allToEntity(groupDTOList);
    }
    private List<GroupDTO> allToDTO(List<Group> groupList) {
        return GroupMapper.GROUP_MAPPER.allToDTO(groupList);
    }
}
