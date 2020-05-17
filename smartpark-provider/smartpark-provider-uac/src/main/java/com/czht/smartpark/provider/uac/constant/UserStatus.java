package com.czht.smartpark.provider.uac.constant;

public enum UserStatus {
    ENABLED(true, "启用"), DISABLED(false, "禁用");

    private boolean status;

    private String name;

    UserStatus(boolean status, String name) {
        this.name = name;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
