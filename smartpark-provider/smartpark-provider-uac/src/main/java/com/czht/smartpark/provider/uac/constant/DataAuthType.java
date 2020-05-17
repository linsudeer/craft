package com.czht.smartpark.provider.uac.constant;

/**
 * 数据权限类
 */
public enum DataAuthType {

    /**
     * 组织机构
     */
    ORGANIZATION("org");

    private String name;

    DataAuthType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
