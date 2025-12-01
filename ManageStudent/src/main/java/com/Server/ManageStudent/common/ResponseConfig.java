package com.Server.ManageStudent.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseConfig<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ResponseConfig() {
        this.timestamp = LocalDateTime.now();
    }

    public ResponseConfig(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ResponseConfig<T> success(T data, String message) {
        return new ResponseConfig<>(true, message, data);
    }

    public static <T> ResponseConfig<T> failure(String message) {
        return new ResponseConfig<>(false, message, null);
    }
}
