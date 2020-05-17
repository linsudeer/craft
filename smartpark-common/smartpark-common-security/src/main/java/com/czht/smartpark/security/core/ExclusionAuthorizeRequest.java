package com.czht.smartpark.security.core;

/**
 * 不需要资源验证的资源
 */
public interface ExclusionAuthorizeRequest {

    /**
     * 这里返回不需要权限验证的资源
     * @return
     */
    String[] exclusion();
}
