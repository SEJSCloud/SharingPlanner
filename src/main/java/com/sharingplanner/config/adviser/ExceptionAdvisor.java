package com.sharingplanner.config.adviser;

import com.sharingplanner.common.enums.ResultType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    public String handleRootException(Exception exception){
        log.error(exception.getMessage());
        return ResultType.FAIL.getResultTypeName();
    }
}
