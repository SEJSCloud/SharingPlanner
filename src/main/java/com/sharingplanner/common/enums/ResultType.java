package com.sharingplanner.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultType {
    SUCCESS("success"),
    FAIL("fail");

    private final String resultTypeName;
}
