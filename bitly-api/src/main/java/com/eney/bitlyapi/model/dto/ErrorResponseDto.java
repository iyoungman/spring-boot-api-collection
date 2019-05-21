package com.eney.bitlyapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {

    private String originalMessage;
    private String userDefineMessage;
    private String requestURL;

    @Builder
    public ErrorResponseDto(String originalMessage, String userDefineMessage, String requestURL) {
        this.originalMessage = originalMessage;
        this.userDefineMessage = userDefineMessage;
        this.requestURL = requestURL;
    }
}
