package com.czht.smartpark.common.base.dto;

import com.czht.smartpark.common.base.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Li Songlin
 * @date 2019/9/20 17:11
 **/
@Getter
@Setter
public class ResultTip {

    private static final Integer OK = 200;

    private static final String MSG = "操作成功";

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据对象
     */
    private Object data = new Object();

    public ResultTip() {}

    public ResultTip(Integer code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.data = obj;
    }

    public ResultTip(ErrorCode code) {
        this.code = code.code();
        this.message = code.msg();
    }


    public ResultTip(ErrorCode code, Object obj) {
        this.code = code.code();
        this.data = obj;
        this.message = code.msg();
    }


    public static ResultTip success() {
        return new ResultTip(OK, MSG, null);
    }

    public static ResultTip success(Object obj) {
        return new ResultTip(OK, MSG, obj);
    }

    public static ResultTip error() {
        return new ResultTip(ErrorCode.ERROR);
    }

    public static ResultTip error(String message) {
        return new ResultTip(ErrorCode.ERROR.code(), message, null);
    }

    public static ResultTip error(ErrorCode code) {
        return new ResultTip(code);
    }

    public static ResultTip error(int code, String message) {
        return new ResultTip(code, message, null);
    }


}
