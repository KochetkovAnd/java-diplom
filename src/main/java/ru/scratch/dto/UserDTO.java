package ru.scratch.dto;

import lombok.Data;
import ru.scratch.domain.Group;
import ru.scratch.domain.enums.Role;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String password;
    private Role role;
    private Group group;
}
