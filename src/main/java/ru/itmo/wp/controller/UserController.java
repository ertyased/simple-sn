package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.UserRegistrationCredentials;
import ru.itmo.wp.repository.UserRepository;
import ru.itmo.wp.service.JwtService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    public UserController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @GetMapping("users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }

    @GetMapping("users")
    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }
    @GetMapping("users/getById/{id}")
    public User findById(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @PostMapping("users")
    public User saveUser(@RequestBody @Valid UserRegistrationCredentials userRegistrationCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        User user = new User();
        user.setLogin(userRegistrationCredentials.getLogin());
        user.setName(userRegistrationCredentials.getName());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), user.getLogin(), userRegistrationCredentials.getPassword());
        return user;
    }

}
