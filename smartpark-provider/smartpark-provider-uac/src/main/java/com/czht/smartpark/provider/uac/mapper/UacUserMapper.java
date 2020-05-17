package com.czht.smartpark.provider.uac.mapper;

import com.czht.smartpark.provider.uac.dmo.UacUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UacUserMapper extends Mapper<UacUser> {

    /**
     * 根据账号查找用户
     * @param name
     * @return
     */
    UacUser getByAccount(@Param("account") String name);

    int disableUser(Integer userId);

    int enableUser(Integer userId);
}