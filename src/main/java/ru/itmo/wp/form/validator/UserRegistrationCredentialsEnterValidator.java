package ru.itmo.wp.form.validator;

import org.springframework.validation.Errors;
import ru.itmo.wp.form.UserRegistrationCredentials;
import ru.itmo.wp.service.UserService;

public class UserRegistrationCredentialsEnterValidator {
    private final UserService userService;

    public UserRegistrationCredentialsEnterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserRegistrationCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserRegistrationCredentials inForm = (UserRegistrationCredentials) target;
            if (userService.findByLogin(inForm.getLogin()) != null) {
                errors.reject("Login is already in user", "Login is already in user");
            }
        }
    }
}
