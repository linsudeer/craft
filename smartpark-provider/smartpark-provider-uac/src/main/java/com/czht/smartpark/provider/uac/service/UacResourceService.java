package com.czht.smartpark.provider.uac.service;

import com.czht.smartpark.provider.uac.dmo.UacResource;

import java.util.List;

public interface UacResourceService {
    /**
     * 查找资源列表
     * @return
     */
    List<UacResource> getResources();

    /**
     * 根据Id查找资源
     * @param resourceId
     * @return
     */
    UacResource gerResourceById(Integer resourceId);

    /**
     * 创建资源
     * @param resource
     * @return
     */
    int createResource(UacResource resource);

    /**
     * 更新资源
     * @param resourceId
     * @param resource
     * @return
     */
    int updateResource(UacResource resource);

    /**
     * 删除资源
     * @param resourceIds
     * @return
     */
    int delResource(String resourceIds);
}
