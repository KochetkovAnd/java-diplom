package ru.scratch.security;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scratch.domain.User;
import ru.scratch.dto.UserDTO;
import ru.scratch.exception.ResourceNotFoundException;
import ru.scratch.mapper.UserMapper;
import ru.scratch.repository.UserRepository;
import ru.scratch.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
            User user = userRepository.findByName(requestDTO.getUsername()).get();
            String token = jwtTokenProvider.createToken(requestDTO.getUsername(), user.getPassword());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", requestDTO.getUsername());
            response.put("token", token);
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username/password combination", HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("register")
    public UserDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if (!userRepository.findByName(registerRequestDTO.getUsername()).isPresent()) {
            User user = new User();
            user.setName(registerRequestDTO.getUsername());
            user.setRole(registerRequestDTO.getRole());
            user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            userRepository.save(user);


            return UserMapper.USER_MAPPER.toDTO(userRepository.findByName(registerRequestDTO.getUsername()).get());
        } else {
            throw new ResourceNotFoundException("User with name = " + registerRequestDTO.getUsername() + " already exist", "");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}