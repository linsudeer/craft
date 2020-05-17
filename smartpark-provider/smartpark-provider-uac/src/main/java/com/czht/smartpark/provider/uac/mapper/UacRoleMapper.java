package com.czht.smartpark.provider.uac.mapper;

import com.czht.smartpark.provider.uac.dmo.UacRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UacRoleMapper extends Mapper<UacRole> {

    List<UacRole> getRolesByUserId(@Param("userId") Integer userId);
}