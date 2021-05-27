package com.mashiro.exception;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 10:20
 */
public class ErrorRequestException extends RuntimeException{
    public ErrorRequestException() {
    }

    public ErrorRequestException(String message) {
        super(message);
    }

    public ErrorRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
