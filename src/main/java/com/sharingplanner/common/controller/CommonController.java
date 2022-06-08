package com.sharingplanner.common.controller;

import com.sharingplanner.common.model.UserRequest;
import com.sharingplanner.common.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommonController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public void signUp(HttpServletResponse response, UserRequest userRequest) throws IOException {
        try {
            userService.signUp(userRequest);
            response.sendRedirect("/login");
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}
