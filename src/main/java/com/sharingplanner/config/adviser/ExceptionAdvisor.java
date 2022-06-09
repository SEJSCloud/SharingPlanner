package com.sharingplanner.config.adviser;

import com.sharingplanner.common.enums.ErrorMessageType;
import com.sharingplanner.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {
    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(Exception exception){
        return handleDefaultError(exception, exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ModelAndView handleMethodArgumentException(Exception exception){
        return handleDefaultError(exception, ErrorMessageType.REQUEST_PARAMETER_VALID_FAIL.getMessage());
    }

    private ModelAndView handleDefaultError(Exception exception, String message){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("customError");

        log.error(exception.getMessage());
        return mav;
    }
}
