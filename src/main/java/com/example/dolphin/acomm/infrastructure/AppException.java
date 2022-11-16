package com.example.dolphin.acomm.infrastructure;

/**
 * 基本异常
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
public class AppException extends RuntimeException {
    private Object ext;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message, Object ext) {
        super(message);
        this.ext = ext;
    }

    public Object getExt() {
        return ext;
    }
}
