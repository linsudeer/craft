package com.czht.smartpark.provider.uac.web.controller;

import com.czht.smartpark.common.base.dto.ResultTip;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public ResultTip hello() {

        return ResultTip.success("hello world");
    }

    @GetMapping("/download")
    public ResultTip download(HttpServletResponse response) {

        String path = "D:\\github\\cloud\\smartpark-parent\\smartpark-provider\\smartpark-provider-uac\\src\\main\\resources\\server.war";

        File file = new File(path);
        String fileName = "server.war";
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buffer = new byte[1024];

        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream outputStream = response.getOutputStream();

            int i = bis.read(buffer);

            while (i != -1) {
                outputStream.write(buffer, 0, i);
                outputStream.flush();
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTip.success();
    }

}
