package com.drhs.utils.exception;

import com.drhs.utils.result.ResponseParams;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理，执行的方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseParams<?> error(Exception e) {
        e.printStackTrace();
        return ResponseParams.fail().message("服务内部错误");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseParams<?> error(ArithmeticException e) {
        e.printStackTrace();
        return ResponseParams.fail().message("执行特定异常处理...");
    }

    //自定义异常处理
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseParams<?> error(CustomException e) {
        e.printStackTrace();
        return ResponseParams.fail().code(e.getCode()).message(e.getMsg());
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseParams<?> error(AccessDeniedException e) throws AccessDeniedException {
        return ResponseParams.fail().code(403).message("没有操作权限");
    }
}
