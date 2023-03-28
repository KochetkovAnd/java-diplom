package ru.scratch.security;

import lombok.Data;
import ru.scratch.domain.enums.Role;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private Role role;
}
