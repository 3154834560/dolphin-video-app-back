package com.example.dolphin.acomm.infrastructure.handler;

import com.example.dolphin.acomm.model.rest.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 基本异常处理
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
@RestControllerAdvice
@Slf4j
public class AcommExceptionHandler {


    /**
     * ClassCastException 特殊处理
     */
    @ExceptionHandler(value = ClassCastException.class)
    public R<?> onClassCastException(ClassCastException e) {
        e.printStackTrace();
        return R.fail("类型转换出错,请检查服务端日志!");
    }

}
