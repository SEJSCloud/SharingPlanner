package com.sharingplanner.common.controller;

import com.sharingplanner.common.exception.CustomException;
import com.sharingplanner.common.model.UserRequest;
import com.sharingplanner.common.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommonController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@Valid UserRequest userRequest) throws CustomException {
        try {
            userService.signUp(userRequest);
        } catch (CustomException e){
            throw e;
        } catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("회원가입에 실패하였습니다.");
        }

        return "redirect:/login";
    }
}
