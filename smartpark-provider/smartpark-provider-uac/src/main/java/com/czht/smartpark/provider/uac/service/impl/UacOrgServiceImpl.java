package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.common.base.exception.NotExistException;
import com.czht.smartpark.provider.uac.dmo.UacOrg;
import com.czht.smartpark.provider.uac.mapper.UacOrgMapper;
import com.czht.smartpark.provider.uac.service.UacOrgService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UacOrgServiceImpl implements UacOrgService {

    @Resource
    private UacOrgMapper uacOrgMapper;

    @Override
    public List<UacOrg> getOrgs() {
        List<UacOrg> orgs = uacOrgMapper.getOrgs();
        return orgs;
    }

    @Override
    public UacOrg getOrgById(Integer orgId) {
        UacOrg org = uacOrgMapper.selectByPrimaryKey(orgId);
        if(org == null) {
            throw new NotExistException("ID为【%d】的组织机构不存在", orgId);
        }
        return org;
    }

    @Override
    public int createOrg(UacOrg org) {
        org.setEnabled(true);
        org.setCreateTime(new Date());
        decorateOrg(org);
        int result = uacOrgMapper.insertSelective(org);
        return result;
    }

    @Override
    public int updateOrg(UacOrg org) {
        UacOrg dbOrg = uacOrgMapper.selectByPrimaryKey(org.getId());
        if(dbOrg == null) {
            throw new NotExistException("组织机构【%s】不存在", org.getOrgName());
        }
        org.setModifyTime(new Date());
        decorateOrg(org);
        return uacOrgMapper.updateByPrimaryKey(org);
    }

    /**
     * 装饰组织结构
     * @param org
     */
    private void decorateOrg(UacOrg org) {
        Integer pid = org.getParentId();
        if(pid != null) {
            UacOrg pOrg = uacOrgMapper.selectByPrimaryKey(pid);
            String pPath = org.getPath();
            if(org != null && !Strings.isEmpty(pPath)) {
                org.setPath(pOrg.getPath().concat(".").concat(String.valueOf(pid)).concat("."));
                org.setLevel(org.getPath().split("\\.").length);
            }else {
                throw new NotExistException("组织机构【%s】父部门不存在", org.getOrgName());
            }
        }else {
            org.setPath("");
            org.setLevel(1);
        }
    }

    @Override
    @Transactional
    public int delOrg(String orgIds) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isEmpty(orgIds), ErrorCode.ARGS_NOT_EXIST.msg());
        String[] ids = orgIds.split(";");
        try {
            if (ids.length > 1) {
                for (String id : ids) {
                    result += uacOrgMapper.deleteByPrimaryKey(NumberUtils.createInteger(id));
                }
            }else {
                result += uacOrgMapper.deleteByPrimaryKey(NumberUtils.createInteger(orgIds.replace(";", "")));
            }
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
        }

        return result;
    }
}
