package com.czht.smartpark.provider.uac.mapper;

import com.czht.smartpark.provider.uac.dmo.UacOrg;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UacOrgMapper extends Mapper<UacOrg> {
    List<UacOrg> getOrgs();
}