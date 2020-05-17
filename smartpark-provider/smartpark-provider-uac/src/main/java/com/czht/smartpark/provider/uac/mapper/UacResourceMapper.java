package com.czht.smartpark.provider.uac.mapper;

import com.czht.smartpark.provider.uac.dmo.UacResource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UacResourceMapper extends Mapper<UacResource> {
    List<UacResource> getResources();
}