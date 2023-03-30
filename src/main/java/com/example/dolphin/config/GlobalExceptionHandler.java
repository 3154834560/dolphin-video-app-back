package com.example.dolphin.config;

import com.example.dolphin.infrastructure.exception.DuplicateException;
import com.example.dolphin.infrastructure.model.rest.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 王景阳
 * @date 2022/10/27 20:44
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * ClassCastException 特殊处理
     */
    @ExceptionHandler(value = ClassCastException.class)
    public R<?> onClassCastException(ClassCastException e) {
        e.printStackTrace();
        return R.fail("类型转换出错,请检查服务端日志!");
    }

    @ExceptionHandler(value = DuplicateException.class)
    public R<?> onException(Exception e) {
        log.error(e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * TODO: 兜底处理,可能不安全(如暴露SQL异常信息)
     */
    @ExceptionHandler(value = Exception.class)
    public R<?> handleException(Exception e) {
        log.error("TODO:兜底处理---{}", e.getMessage(), e);
        return R.fail(e.getMessage());
    }
}
