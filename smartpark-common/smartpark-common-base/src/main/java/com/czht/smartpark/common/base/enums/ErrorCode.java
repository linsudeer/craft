package com.czht.smartpark.common.base.enums;


/**
 * The class Error code enum.
 *
 */
public enum ErrorCode {

	RPC_ERROR(1001, "微服务调用异常"),

	ARGS_ERROR(1002, "参数错误"),

	ARGS_ILLEGAL(1003, "非法参数"),

	NAME_EXIST(1004, "名称已存在"),

	UNAUTHORIZED(1005, "没有访问权限"),

	/**
	 * 数据库唯一索引异常，页面显示可根据错误码，返回具体的业务提示，例如用户名已存在等
	 */
	DUPLICATE_KEY(1006, "字段重复"),

	NOT_EXIST(1007, "数据不存在"),

	ARGS_NOT_EXIST(1008,"参数不存在"),

	DATA_INTEGRITY_VIOLATION(1009, "数据已被绑定"),

	UNAUTHORIZED_RESOURCE(1010, "没有访问权限，请联系管理员配置"),

	ERROR(500, "系统出错"),

	;

	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the code
	 *
	 * @return the enum
	 */
	public static ErrorCode getEnum(int code) {
		for (ErrorCode ele : ErrorCode.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
