package com.czht.smartpark.common.core.log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 日志动作
 */
@Getter
public enum  LogAction {

    ADD(1, "新增"), UPDATE(2, "修改"), DELETE(3, "删除"), SELECT(4, "查询");

    private Integer action;

    private String name;

    LogAction(Integer action, String name) {
        this.action = action;
        this.name = name;
    }



    /**
     * Gets name.
     *
     * @param type the type
     *
     * @return the name
     */
    public static String getName(Integer type) {
        for (LogAction ele : LogAction.values()) {
            if (type == ele.getAction()) {
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
    public static LogAction getEnum(Integer type) {
        for (LogAction ele : LogAction.values()) {
            if (type == ele.getAction()) {
                return ele;
            }
        }
        return LogAction.SELECT;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = Lists.newArrayList();
        for (LogAction ele : LogAction.values()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("key", ele.getAction());
            map.put("value", ele.getName());
            list.add(map);
        }
        return list;
    }

}
