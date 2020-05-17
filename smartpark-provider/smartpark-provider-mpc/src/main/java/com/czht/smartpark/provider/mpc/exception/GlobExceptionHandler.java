package com.czht.smartpark.provider.mpc.exception;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.xiaoleilu.hutool.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author Li Songlin
 *
 * 统一异常处理器
 * @date 2019/9/24 11:12
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobExceptionHandler {

    /**
     * 验证异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultTip methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append("；"));

        log.error("参数校验异常："+ ExceptionUtil.getMessage(exception));

        return ResultTip.error(ErrorCode.ARGS_ERROR.code(), exception.getMessage());
    }

    /**
     * 参数非法异常.
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResultTip illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.ARGS_ILLEGAL.code(), e.getMessage());
    }


    /**
     * 无权限访问.
     *
     * @param e the e
     *
     * @return the wrapper
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultTip unAuthorizedException(AccessDeniedException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.UNAUTHORIZED);
    }


    /**
     * 其他运行时异常（开发注意避免发生）
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultTip runTimeExceptionHandler(RuntimeException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.ERROR);

    }


}
