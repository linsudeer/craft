package com.czht.smartpark.provider.mpc.web;

import com.czht.smartpark.common.base.dto.ResultTip;
import com.czht.smartpark.provider.mpc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/mpc")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/current")
    public ResultTip getCurrentUser(Principal principal) {
        return helloService.getUserByName("admin");
    }

    @GetMapping("/hello")
    public ResultTip hello() {
        return ResultTip.success("hello");
    }
}
