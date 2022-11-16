package com.example.dolphin.config;

import com.example.dolphin.acomm.model.rest.R;
import com.example.dolphin.infrastructure.exception.DuplicateException;
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
