package com.example.dolphin.infrastructure.exception;

/**
 * @author 王景阳
 * @date 2022/10/27 20:44
 */
public class DuplicateException extends RuntimeException{

    public DuplicateException(String msg){
        super(msg);
    }
}
