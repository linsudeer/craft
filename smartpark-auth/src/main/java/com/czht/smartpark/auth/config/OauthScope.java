package com.czht.smartpark.auth.config;

public enum OauthScope {
    UI("ui"), SERVER("server");

    private String scope;

    OauthScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
