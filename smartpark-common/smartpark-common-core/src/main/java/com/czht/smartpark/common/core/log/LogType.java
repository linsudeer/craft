package com.czht.smartpark.common.core.log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


/**
 * 日志类型
 *
 */
public enum LogType {
	/**
	 * 操作日志
	 */
	OPERATION_LOG(1, "操作日志"),
	/**
	 * 登录日志
	 */
	LOGIN_LOG(2, "登录日志"),
	/**
	 * 异常日志
	 */
	EXCEPTION_LOG(3, "异常日志");

	/**
	 * The Type.
	 */
	Integer type;
	/**
	 * The Name.
	 */
	String name;

	LogType(Integer type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets name.
	 *
	 * @param type the type
	 *
	 * @return the name
	 */
	public static String getName(Integer type) {
		for (LogType ele : LogType.values()) {
			if (type == ele.getType()) {
				return ele.getName();
			}
		}
		return null;
	}

	/**
	 * Gets enum.
	 *
	 * @param type the type
	 *
	 * @return the enum
	 */
	public static LogType getEnum(Integer type) {
		for (LogType ele : LogType.values()) {
			if (type == ele.getType()) {
				return ele;
			}
		}
		return LogType.OPERATION_LOG;
	}

	/**
	 * Gets list.
	 *
	 * @return the list
	 */
	public static List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = Lists.newArrayList();
		for (LogType ele : LogType.values()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("key", ele.getType());
			map.put("value", ele.getName());
			list.add(map);
		}
		return list;
	}

}
