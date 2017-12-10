package com.qds.optimize.zuul.hello.web;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传对外提供的服务
 *
 * @author neutron
 */
@RestController
@RequestMapping(value = "/hello")
public class FileApi {

    /**
     * 在spring boot下的resources目录下创建METE-INF目录,然后在创建resources目录<br/>
     * 该目录下的文件或者页面可以直接访问,这是servlet3.0规范
     * <p>
     * 如果想走zuul,但是不想走zuul的分发器,那么只需要在请求前面加上/zuul/,<br/>
     * 不然配置zuul,直接调用其他的服务,以及需要配置上传文件的配置
     */
    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.err.println("文件名称:" + file.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(new File("/tmp"), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "upload file error";
        }
        return "upload file success";
    }

}
