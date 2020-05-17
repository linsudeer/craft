package com.czht.smartpark.auth.mapper;

import com.czht.smartpark.auth.dmo.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    List<Role> getRolesByUserId(@Param("userId") Integer userId);
}