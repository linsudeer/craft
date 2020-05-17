package com.czht.smartpark.provider.uac.exception;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.common.base.exception.NotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultTip methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append("；"));

        log.error("参数校验异常："+ exception.getMessage());

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultTip illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.ARGS_ILLEGAL.code(), e.getMessage());
    }

    /**
     * 数据库字段重复
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResultTip duplicateKeyException(DuplicateKeyException e) {
        log.error("数据库KEY重复={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.DUPLICATE_KEY);
    }

    /**
     * 外键异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResultTip dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("数据库外键异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.DATA_INTEGRITY_VIOLATION);
    }

    /**
     * 用户名不存在
     * @param e
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResultTip usernameNotFoundException(UsernameNotFoundException e) {
        log.error("用户名不存在={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.NOT_EXIST.code(), e.getMessage());
    }

    /**
     * 数据不存在
     * @param e
     * @return
     */
    @ExceptionHandler(NotExistException.class)
    @ResponseBody
    public ResultTip notExistException(NotExistException e) {
        log.error("不存在={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.NOT_EXIST.code(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultTip businessException(BusinessException e){
        log.error("业务异常={}", e.getMessage(), e);
        return ResultTip.error(e.getCode(), e.getMessage());
    }

    /**
     * 其他运行时异常（开发注意避免发生）
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultTip runTimeExceptionHandler(RuntimeException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return ResultTip.error(ErrorCode.ERROR);

    }


}
