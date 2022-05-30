package config.security.controller;

import config.security.model.CustomUser;
import config.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth/login")
    public String login( @RequestBody CustomUser user){
        return userService.login(user);
    }
}
