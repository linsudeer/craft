package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.common.base.exception.NotExistException;
import com.czht.smartpark.provider.uac.dmo.UacResource;
import com.czht.smartpark.provider.uac.mapper.UacResourceMapper;
import com.czht.smartpark.provider.uac.service.UacResourceService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UacResourceServiceImpl implements UacResourceService {

    @Resource
    private UacResourceMapper uacResourceMapper;

    @Override
    public List<UacResource> getResources() {
        List<UacResource> resources = uacResourceMapper.getResources();
        return resources;
    }

    @Override
    public UacResource gerResourceById(Integer resourceId) {
        UacResource resource = uacResourceMapper.selectByPrimaryKey(resourceId);
        if (resource == null) {
            throw new NotExistException("ID为【%d】的资源不存在", resourceId);
        }
        return resource;
    }

    @Override
    public int createResource(UacResource resource) {
        resource.setCreateTime(new Date());
        int result = uacResourceMapper.insertSelective(resource);
        return result;
    }

    @Override
    public int updateResource(UacResource resource) {
        UacResource dbResource = uacResourceMapper.selectByPrimaryKey(resource.getId());
        if (dbResource == null) {
            throw new NotExistException("资源 %s 不存在", resource.getResourceName());
        }
        resource.setModifyTime(new Date());
        int result = uacResourceMapper.updateByPrimaryKey(resource);
        return result;
    }

    @Override
    @Transactional
    public int delResource(String resourceIds) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isNullOrEmpty(resourceIds), ErrorCode.ARGS_NOT_EXIST.msg());
        String[] ids = resourceIds.split(";");
        try {
            if (ids.length > 1) {
                for (String id : ids) {
                    result += uacResourceMapper.deleteByPrimaryKey(NumberUtils.createInteger(id));
                }
            } else {
                result += uacResourceMapper.deleteByPrimaryKey(NumberUtils.createInteger(resourceIds.replace(";", "")));

            }
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
        }

        return result;
    }
}
