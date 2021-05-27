package com.mashiro.exception;

/**
 * @Description: 持久化异常
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:55
 */
public class PersistenceException extends RuntimeException{
    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
