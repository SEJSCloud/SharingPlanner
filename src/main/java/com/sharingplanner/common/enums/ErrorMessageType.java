package com.sharingplanner.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageType {
    REQUEST_PARAMETER_VALID_FAIL("올바른 형식으로 입력해주세요.");

    private final String message;
}
