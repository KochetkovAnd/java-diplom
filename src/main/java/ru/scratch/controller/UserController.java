package ru.scratch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scratch.dto.UserDTO;
import ru.scratch.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getAllByGroupId/{id}")
    public List<UserDTO> getAllByGroupId(@PathVariable Long id) {
        return userService.getAllByGroupId(id);
    }

    @GetMapping("/getAllStudentsWithoutGroup")
    public List<UserDTO> getAllStudentsWithoutGroup() {
        return userService.getAllStudentsWithoutGroup();
    }

    @GetMapping("/getAllTeachers")
    public List<UserDTO> getAllTeachers() {
        return userService.getAllTeachers();
    }


    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/sureDeleteById/{id}")
    public boolean sureDeleteById(@PathVariable Long id) {
        return userService.sureDeleteById(id);
    }

    @GetMapping("/canDeleteById/{id}")
    public boolean canDeleteById(@PathVariable Long id) {
        return userService.canDeleteById(id);
    }

}
