package com.czht.smartpark.provider.uac.service.impl;

import com.czht.smartpark.common.base.enums.ErrorCode;
import com.czht.smartpark.common.base.exception.BusinessException;
import com.czht.smartpark.common.base.exception.NotExistException;
import com.czht.smartpark.provider.uac.dmo.UacMenu;
import com.czht.smartpark.provider.uac.mapper.UacMenuMapper;
import com.czht.smartpark.provider.uac.service.UacMenuService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UacMenuServiceImpl implements UacMenuService {

    @Resource
    private UacMenuMapper uacMenuMapper;

    @Override
    public List<UacMenu> getMenus() {
        return uacMenuMapper.getMenus();
    }

    @Override
    public UacMenu getMenuById(Integer menuId) {
        UacMenu menu = uacMenuMapper.selectByPrimaryKey(menuId);
        if (menu == null) {
            throw new NotExistException("ID为%d的菜单不存在", menuId);
        }
        return menu;
    }

    @Override
    public int createMenu(UacMenu menu) {
        menu.setCreateTime(new Date());
        int result = uacMenuMapper.insertSelective(menu);
        return result;
    }

    @Override
    public int updateMenu(UacMenu menu) {
        UacMenu qMenu = uacMenuMapper.selectByPrimaryKey(menu);
        if (qMenu == null) {
            throw new NotExistException("菜单 %s 不存在", menu.getMenuName());
        }

        menu.setModifyTime(new Date());
        int result = uacMenuMapper.updateByPrimaryKey(menu);
        return result;
    }

    @Override
    @Transactional
    public int delMenu(String menuIds) {
        int result = 0;
        Preconditions.checkArgument(!Strings.isNullOrEmpty(menuIds), ErrorCode.ARGS_NOT_EXIST.msg());
        String[] ids = menuIds.split(";");
        try {
            if (ids.length > 1) {
                for (String id : ids) {
                    result += uacMenuMapper.deleteByPrimaryKey(NumberUtils.createInteger(id));
                }
            }else {
                result += uacMenuMapper.deleteByPrimaryKey(NumberUtils.createInteger(menuIds.replace(";", "")));
            }
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.ARGS_ILLEGAL);
        }

        return result;
    }

    @Override
    public List<UacMenu> getMenusByUserId(Integer userId) {

        return uacMenuMapper.getMenusByUserId(userId);
    }
}
