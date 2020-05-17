package com.czht.smartpark.auth.web.controller;

import com.czht.smartpark.auth.dmo.User;
import com.czht.smartpark.auth.service.UserService;
import com.czht.smartpark.common.base.dto.ResultTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService uacUserService;

//    @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
    @GetMapping(path = "/{account}")
    public ResultTip getByAccount(@PathVariable String account) {
        User user = uacUserService.getByAccount(account);
        return ResultTip.success(user);
    }

    @GetMapping(path = "/current")
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

}
